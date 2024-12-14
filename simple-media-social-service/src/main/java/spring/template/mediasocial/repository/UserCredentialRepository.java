package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.UserCredentialEntity;
import spring.template.mediasocial.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredentialEntity, Long> {

    public boolean existsByLoginIdentifier(String identifier);
    Optional<UserCredentialEntity> findByLoginIdentifier(String loginIdentifier);
}
