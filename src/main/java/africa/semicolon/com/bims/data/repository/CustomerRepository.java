package africa.semicolon.com.bims.data.repository;

import africa.semicolon.com.bims.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String customerEmail);
}
