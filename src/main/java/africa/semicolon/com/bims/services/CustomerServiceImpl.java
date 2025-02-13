package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Cart;
import africa.semicolon.com.bims.data.model.Customer;
import africa.semicolon.com.bims.data.model.Order;
import africa.semicolon.com.bims.data.repository.CustomerRepository;
import africa.semicolon.com.bims.dtos.requests.*;
import africa.semicolon.com.bims.dtos.responses.*;
import africa.semicolon.com.bims.exceptions.EmailExistException;
import africa.semicolon.com.bims.exceptions.OrderNotFoundException;
import africa.semicolon.com.bims.exceptions.PictureUploadFailedException;
import africa.semicolon.com.bims.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import utils.JwtUtils;

import java.io.IOException;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    @Lazy
    private OrderService orderService;
    @Autowired
    private CartServices cartServices;
    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest request) {
        validateExistingCustomer(request.getCustomerEmail());
        try {
            Customer customer = modelMapper.map(request, Customer.class);
            customer = customerRepository.save(customer);
            Cart cart = new Cart();
            cart.setCustomerId(customer.getId());
            customer.setCart(cart);
            cartServices.save(cart);
            customerRepository.save(customer);
            RegisterCustomerResponse response = new RegisterCustomerResponse();
            response.setMessage("WELCOME TO SOFT STORE");
            return modelMapper.map(response, RegisterCustomerResponse.class);
        }catch(PictureUploadFailedException e){
            throw new PictureUploadFailedException(e.getMessage());
        }
    }

    private void validateExistingCustomer(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        if(customer!= null) throw new EmailExistException("Email Already Exist");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return checkLoginDetail(email, password);
    }

    private LoginResponse checkLoginDetail(String email, String password) {
        Customer optionalUser = customerRepository.findByEmail(email);
        if (optionalUser!= null){
            if (optionalUser.getPassword().equals(password)) {
                return loginResponseMapper(optionalUser);
            } else {
                throw new UserNotFoundException("Invalid username or password");
            }
        } else {
            throw new UserNotFoundException("Invalid username or password");
        }
    }

    private LoginResponse loginResponseMapper(Customer user) {
        LoginResponse loginResponse = new LoginResponse();
        String accessToken = JwtUtils.generateAccessToken(user.getId());
        BeanUtils.copyProperties(user, loginResponse);
        loginResponse.setJwtToken(accessToken);
        loginResponse.setMessage("Login Successful");
        loginResponse.setRole(user.getRole().toString());
        return loginResponse;
    }

    @Override
    public void customerOrders(Order order, Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public AddItemResponse addItemToCart(AddDesignRequest addItemToCartRequest) throws IOException {
        return cartServices.addItemToCart(addItemToCartRequest);
    }

    @Override
    public String removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new UserNotFoundException("User Not Found:"));
         cartServices.removeItemFromCart(removeItemFromCartRequest, customer);
         return "item removed";
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerBy(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()->new IllegalArgumentException("user not found"));
    }

    @Override
    public List<OrderResponse> getListOfOrders(Long customerId) {
        List<OrderResponse> orders = orderService.getOrderFor(customerId);
        if(orders.isEmpty())throw new OrderNotFoundException("Order Not Found ");
        return orders;
    }





}
