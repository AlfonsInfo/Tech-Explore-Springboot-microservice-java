package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.UserSignupEntity;

import java.util.Optional;

public interface UserSignupRepository extends JpaRepository<UserSignupEntity,Long> {
    // find
    Optional<UserSignupEntity> findByEmailOrPhoneNumber(String email, String phoneNumber);

    default Optional<UserSignupEntity> findByCredentialIdentifier(String identifier){
        return findByEmailOrPhoneNumber(identifier,identifier);
    }

    // exist
    boolean existsByPhoneNumberOrEmail(String phoneNumber, String email);
    default boolean existsByPhoneNumberOrEmail(String identifier){
        return existsByPhoneNumberOrEmail(identifier,identifier);
    }
}
