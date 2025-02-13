package africa.semicolon.com.bims.dtos.responses;

import lombok.Data;

@Data
public class AddItemResponse {
    private Long cartId;
    private Long itemId;
    private String message;
}
