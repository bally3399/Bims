package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Cart;
import africa.semicolon.com.bims.data.model.Customer;
import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.RemoveItemFromCartRequest;
import africa.semicolon.com.bims.dtos.responses.AddItemResponse;

import java.io.IOException;
import java.util.List;


public interface CartServices {
    AddItemResponse addItemToCart(AddDesignRequest addItemToCartRequest) throws IOException;

//    Long countCustomerItem(Long customerId);

    void removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest, Customer customer);

    List<Design> viewCart(Long id);


    Cart findCart(Long cartId);

    Cart save(Cart cart);
}

