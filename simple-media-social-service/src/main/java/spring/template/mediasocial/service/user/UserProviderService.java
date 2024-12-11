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
        return userRepository
                .findById(Long.parseLong(request.getHeader(HeaderConstant.ID_USER)))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
