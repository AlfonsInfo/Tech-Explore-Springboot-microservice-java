package spring.template.mediasocial.service.login_admin;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.admin_auth.ReqTokenLoginAdminDto;
import spring.template.mediasocial.dto.admin_auth.ResTokenLoginAdminDto;
import spring.template.mediasocial.entity.AdminCredential;
import spring.template.mediasocial.repository.AdminCredentialRepository;
import spring.template.mediasocial.service.jwt.JwtService;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminCredentialService {
    private final AdminCredentialRepository adminCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResTokenLoginAdminDto getLoginAdminToken(ReqTokenLoginAdminDto request) {
        AdminCredential adminCredential = adminCredentialRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("user not found"));
        if (passwordEncoder.matches(request.getPassword(), adminCredential.getPassword())) {
            Map<String, Object> extraClaims = getExtraClaims(adminCredential);
            return ResTokenLoginAdminDto
                    .builder()
                    .tokens(jwtService.createJwtToken(extraClaims))
                    .build();
        } else {
            return ResTokenLoginAdminDto
                    .builder()
                    .tokens("")
                    .build();
        }
    }

    public ResponseEntity<?> getLoginAdminTokensAndSetHttpOnlyCookies(ReqTokenLoginAdminDto request) {
        AdminCredential adminCredential = adminCredentialRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("user not found"));
        if (passwordEncoder.matches(request.getPassword(), adminCredential.getPassword())) {
            Map<String, Object> extraClaims = getExtraClaims(adminCredential);
            String tokens = jwtService.createJwtToken(extraClaims);

            ResponseCookie cookie = getHttpOnlyCookies(tokens);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @NotNull
    private static ResponseCookie getHttpOnlyCookies(String tokens) {
        // Buat HTTP-Only Cookie
        ResponseCookie cookie = ResponseCookie.from("token", tokens)
                .httpOnly(true) // Tidak bisa diakses via JavaScript
                .secure(true) // Wajib HTTPS (pastikan diaktifkan di production)
                .sameSite("Strict") // Atau Lax untuk mengurangi risiko CSRF
                .path("/") // Berlaku untuk seluruh path di domain
                .maxAge(Duration.ofHours(1)) // Waktu berlaku cookie
                .build();
        return cookie;
    }

    @NotNull
    private static Map<String, Object> getExtraClaims(AdminCredential adminCredential) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", adminCredential.getId());
        extraClaims.put("email", adminCredential.getEmail());
        return extraClaims;
    }

}
