package africa.semicolon.com.bims.services;


import africa.semicolon.com.bims.data.model.Customer;
import africa.semicolon.com.bims.data.model.Order;
import africa.semicolon.com.bims.dtos.requests.*;
import africa.semicolon.com.bims.dtos.responses.*;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest request) throws MessagingException;

    LoginResponse login(LoginRequest loginRequest);

    void customerOrders(Order order, Customer customer);

    AddItemResponse addItemToCart(AddDesignRequest addItemToCartRequest) throws IOException;

    String removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest, Long customerId);


    Customer save(Customer customer);

    Customer findCustomerBy(Long customerId);

    List<OrderResponse> getListOfOrders(Long customerId);



}


