package africa.semicolon.com.bims.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditProductRequest {
    private Long productId;
    private String productName = "";
    private String productDescription = "";
}
