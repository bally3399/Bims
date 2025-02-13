package africa.semicolon.com.bims.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.UUID;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cart> carts;

    private String userNote;
    private String platform;

    private LocalDateTime createdAt;
    private Long customerId;


}

