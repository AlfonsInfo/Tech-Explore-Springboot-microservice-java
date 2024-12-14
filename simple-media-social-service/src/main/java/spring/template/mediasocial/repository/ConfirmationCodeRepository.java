package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.template.mediasocial.entity.ConfirmationCodeEntity;

import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCodeEntity, Long> {
    boolean existsByCredentialIdentifierAndCode(String credentialIdentifier, String code);
    ConfirmationCodeEntity findByCredentialIdentifierAndCode(String credentialIdentifier, String code);
    Optional<ConfirmationCodeEntity> findByCredentialIdentifier(String credentialIdentifier);
}
