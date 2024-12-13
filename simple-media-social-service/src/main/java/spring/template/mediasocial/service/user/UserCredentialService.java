package spring.template.mediasocial.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.repository.BlacklistCredentialRepository;
import spring.template.mediasocial.repository.UserCredentialRepository;
import spring.template.mediasocial.repository.UserSignupRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;
    private final UserSignupRepository userSignupRepository;
    private final BlacklistCredentialRepository blacklistCredentialRepository;
    public boolean isAccountBanned(){
        return true;
    }

    public boolean isCredentialIdentifierExist(){
        return true;
    }

    /**
     * Checks if the given credential identifier is not available for a new account.
     *
     * @param identifier the credential identifier to check
     * @return {@code true} if the identifier is available, {@code false} otherwise
     *  Check on table user_credential & user signup
     */
    public boolean isCredentialIdentifierNotAvailableForNewAccount(String identifier) {
        return userCredentialRepository.existsByLoginIdentifier(identifier) ||
                userSignupRepository.existsByPhoneNumberOrEmail(identifier);
    }

    /**
     * Checks if the given credential identifier is blacklisted.
     *
     * @param identifier the credential identifier to check
     * @return {@code true} if the identifier is blacklisted, {@code false} otherwise
     */
    public boolean isCredentialIdentifierBlacklisted(String identifier){
        return blacklistCredentialRepository.existsByLoginIdentifier(identifier);
    }

    public boolean isCredentialMatch(){
        return true;
    }
}
