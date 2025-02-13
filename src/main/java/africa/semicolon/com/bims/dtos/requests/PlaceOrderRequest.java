package africa.semicolon.com.bims.dtos.requests;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private Long customerId;
    private Long cartId;
  //  private long sellerId;
}
