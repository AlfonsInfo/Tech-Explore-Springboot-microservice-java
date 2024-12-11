package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.UserCredentialEntity;
import spring.template.mediasocial.entity.UserEntity;

public interface UserCredentialRepository extends JpaRepository<UserCredentialEntity, Long> {
}
