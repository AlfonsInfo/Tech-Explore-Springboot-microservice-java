package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
