package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.template.mediasocial.entity.ConfirmationCodeEntity;

@Repository
public interface VerificationCodeRepository extends JpaRepository<ConfirmationCodeEntity, Long> {
}
