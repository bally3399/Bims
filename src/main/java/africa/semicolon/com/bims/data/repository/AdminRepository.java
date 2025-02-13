package africa.semicolon.com.bims.data.repository;

import africa.semicolon.com.bims.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    boolean existsByEmail(String email);
}
