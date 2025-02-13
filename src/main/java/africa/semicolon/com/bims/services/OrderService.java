package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Order;
import africa.semicolon.com.bims.dtos.requests.PlaceOrderRequest;
import africa.semicolon.com.bims.dtos.responses.OrderResponse;
import africa.semicolon.com.bims.dtos.responses.PlaceOrderResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface OrderService {
    PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) throws MessagingException;

    Order saveOrder(Order order);

    void deleteOrderById(Long orderId);

    List<OrderResponse> getOrderFor(Long id);

}
