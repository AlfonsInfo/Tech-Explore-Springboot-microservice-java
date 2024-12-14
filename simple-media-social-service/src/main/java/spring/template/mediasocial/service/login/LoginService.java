package spring.template.mediasocial.service.login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.repository.AdminCredentialRepository;
import spring.template.mediasocial.service.jwt.JwtService;



@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
//    private final

    private final AdminCredentialRepository adminCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

}
