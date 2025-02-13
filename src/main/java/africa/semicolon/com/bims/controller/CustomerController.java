package africa.semicolon.com.bims.controller;

import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.LoginRequest;
import africa.semicolon.com.bims.dtos.requests.RegisterCustomerRequest;
import africa.semicolon.com.bims.dtos.requests.RemoveItemFromCartRequest;
import africa.semicolon.com.bims.dtos.responses.*;
import africa.semicolon.com.bims.services.CustomerService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterCustomerResponse> registerCustomer(@RequestBody RegisterCustomerRequest request) throws MessagingException {
        var result = customerService.registerCustomer(request);
        return ResponseEntity.status(CREATED).body(result);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var result = customerService.login(loginRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add_item_to_cart/{customer_id}")
    public ResponseEntity<AddItemResponse> addItemToCart(@RequestBody AddDesignRequest addItemToCartRequest, @PathVariable("customer_id") Long customerId) throws IOException {
        AddItemResponse result = customerService.addItemToCart(addItemToCartRequest);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("remove_item_from_cart/{customer_id}")
    public ResponseEntity<String> removeItemFromCart(@RequestBody RemoveItemFromCartRequest removeItemFromCartRequest, @PathVariable("customer_id") Long customerId) {
        var result = customerService.removeItemFromCart(removeItemFromCartRequest, customerId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get_list_of_order/{customer_id}")
    public ResponseEntity<List<OrderResponse>> getListOfOrders(@PathVariable("customer_id") Long customerId) {
        var result = customerService.getListOfOrders(customerId);
        return ResponseEntity.ok(result);
    }


}
