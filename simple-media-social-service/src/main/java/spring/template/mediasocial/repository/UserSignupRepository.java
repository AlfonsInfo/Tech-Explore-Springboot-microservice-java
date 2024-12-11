package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.UserSignupEntity;

public interface UserSignupRepository extends JpaRepository<UserSignupEntity,Long> {

}
