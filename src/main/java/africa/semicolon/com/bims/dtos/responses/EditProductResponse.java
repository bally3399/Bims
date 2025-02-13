package africa.semicolon.com.bims.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class EditProductResponse {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private long sellerId;
    private Long id;
    private String url;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateCreates;
}
