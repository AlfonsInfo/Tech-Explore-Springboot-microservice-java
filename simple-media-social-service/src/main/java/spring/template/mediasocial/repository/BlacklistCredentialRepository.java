package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.BlacklistCredentialEntity;
import spring.template.mediasocial.entity.UserCredentialEntity;

public interface BlacklistCredentialRepository extends JpaRepository<BlacklistCredentialEntity, Long> {
    public boolean existsByLoginIdentifier(String identifier);
}
