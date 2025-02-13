package africa.semicolon.com.bims.dtos.responses;

import africa.semicolon.com.bims.data.model.Cart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Long customerId;
    private Cart cart;
    private BigDecimal amount;
    private LocalDate orderedDate;
    private LocalDate deliveryDate;
}
