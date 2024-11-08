package spring.template.mediasocial.service.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.constant.HeaderConstant;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProviderService {

    private final HttpServletRequest request;
    private final UserRepository userRepository;
    public UserEntity getLoggedInUser(){
        UUID idUserLogin = UUID.fromString(request.getHeader(HeaderConstant.ID_USER));
        return userRepository
                .findById(idUserLogin)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
    }
}
