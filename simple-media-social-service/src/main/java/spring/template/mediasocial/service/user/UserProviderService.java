package spring.template.mediasocial.service.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.constant.HeaderConstant;
import spring.template.mediasocial.entity.UserCredentialEntity;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.repository.UserCredentialRepository;
import spring.template.mediasocial.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProviderService implements UserDetailsService {

    private final HttpServletRequest request;
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    public UserEntity getLoggedInUser(){
        return userRepository
                .findById(Long.parseLong(request.getHeader(HeaderConstant.ID_USER)))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Optional<UserCredentialEntity> userCredential = userCredentialRepository.findByLoginIdentifier(identifier);
        if(userCredential.isEmpty()){ throw new EntityNotFoundException("Credential not found");}
        UserCredentialEntity userCredentialEntity = userCredential.get();
        return new User(userCredentialEntity.getLoginIdentifier(),  userCredentialEntity.getPassword(), new ArrayList<>());
    }
}
