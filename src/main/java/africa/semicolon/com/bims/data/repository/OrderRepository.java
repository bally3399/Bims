package africa.semicolon.com.bims.data.repository;

import africa.semicolon.com.bims.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByCustomerId(Long id);
}
