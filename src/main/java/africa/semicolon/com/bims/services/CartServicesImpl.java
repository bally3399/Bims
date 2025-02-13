package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Cart;
import africa.semicolon.com.bims.data.model.Customer;
import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.data.repository.CartRepository;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.RemoveItemFromCartRequest;
import africa.semicolon.com.bims.dtos.responses.AddItemResponse;
import africa.semicolon.com.bims.exceptions.DesignNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CartServicesImpl implements CartServices{
    @Autowired
    private DesignService designService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public AddItemResponse addItemToCart(AddDesignRequest addItemToCartRequest) throws IOException {
        Design item =  designService.createDesign(addItemToCartRequest);
        Cart cart = mapper.map(addItemToCartRequest, Cart.class);
        cart.getDesigns().add(item);
        cartRepository.save(cart);
        AddItemResponse response = mapper.map(cart, AddItemResponse.class);
        response.setMessage("Admin registered successfully");
        return response;

    }

//    @Override
//    public Long countCustomerItem(Long customerId) {
//        return cartRepository.countCustomerItem(customerId);
//    }

    @Override
    public void removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest, Customer customer) {
        customer.getCart().getDesigns().remove(designService.findItem(removeItemFromCartRequest.getItemId()));
    }

    @Override
    public List<Design> viewCart(Long id) {
        return designService.get(id);
    }


    @Override
    public Cart findCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(()->new DesignNotFoundException("No Cart Found"));
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
