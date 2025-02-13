package africa.semicolon.com.bims.dtos.requests;

import lombok.Data;

@Data
public class AddItemToCartRequest {
    private Long productId;
    private Long itemId;
    private int quantity;
    private Long customerId;
}
