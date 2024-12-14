package spring.template.mediasocial.service.login_admin;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.admin_auth.ReqTokenLoginAdminDto;
import spring.template.mediasocial.dto.admin_auth.ResTokenLoginAdminDto;
import spring.template.mediasocial.entity.AdminCredential;
import spring.template.mediasocial.repository.AdminCredentialRepository;
import spring.template.mediasocial.service.jwt.JwtService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminCredentialService {
    private final AdminCredentialRepository adminCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResTokenLoginAdminDto getLoginAdminToken(ReqTokenLoginAdminDto request){
        AdminCredential adminCredential = adminCredentialRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("user not found"));
        if(passwordEncoder.matches(request.getPassword(),adminCredential.getPassword())){
            Map<String, Object> extraClaims = getExtraClaims(adminCredential);
            return ResTokenLoginAdminDto
                    .builder()
                    .tokens(jwtService.createJwtToken(extraClaims))
                    .build();
    }else{
        return ResTokenLoginAdminDto
                .builder()
                .tokens(jwtService.createJwtToken(Map.of()))
                .build();
    }
}

@NotNull
private static Map<String, Object> getExtraClaims(AdminCredential adminCredential) {
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("id", adminCredential.getId());
    extraClaims.put("email", adminCredential.getEmail());
    return extraClaims;
}

}
