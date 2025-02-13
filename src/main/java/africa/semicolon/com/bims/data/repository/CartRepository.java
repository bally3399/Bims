package africa.semicolon.com.bims.data.repository;

import africa.semicolon.com.bims.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    Long countCustomerItem(Long customerId);
}
