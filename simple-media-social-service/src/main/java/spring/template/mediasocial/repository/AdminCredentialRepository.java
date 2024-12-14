package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.AdminCredential;
import spring.template.mediasocial.entity.UserCredentialEntity;

import java.util.List;
import java.util.Optional;

public interface AdminCredentialRepository extends JpaRepository<AdminCredential, Long> {
       Optional<AdminCredential> findByEmail(String email);
}
