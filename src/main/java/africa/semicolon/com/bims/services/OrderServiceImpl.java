package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Cart;
import africa.semicolon.com.bims.data.model.Order;
import africa.semicolon.com.bims.data.repository.OrderRepository;
import africa.semicolon.com.bims.dtos.requests.PlaceOrderRequest;
import africa.semicolon.com.bims.dtos.responses.OrderResponse;
import africa.semicolon.com.bims.dtos.responses.PlaceOrderResponse;
import africa.semicolon.com.bims.exceptions.OrderNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderRepository orders;
    @Autowired
    private CartServices cartServices;
    @Override
    @Transactional
    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) throws MessagingException {
        Cart cart = cartServices.findCart(placeOrderRequest.getCartId());
        Order order = mapper.map(placeOrderRequest, Order.class);
        order = orders.save(order);
        return mapper.map(order, PlaceOrderResponse.class);
    }



    @Override
    public Order saveOrder(Order order) {
        return orders.save(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
       Order order = orders.findById(orderId).orElseThrow(()->new OrderNotFoundException("No Order Found "));
       if(order == null) throw new OrderNotFoundException("No Order Found ");
       orders.deleteById(orderId);
    }

    @Override
    public List<OrderResponse> getOrderFor(Long id) {
        return orders.findOrderByCustomerId(id).stream().map((order)->mapper.map(order, OrderResponse.class)).toList();
    }


}
