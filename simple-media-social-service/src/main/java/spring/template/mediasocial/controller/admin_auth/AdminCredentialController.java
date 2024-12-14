package spring.template.mediasocial.controller.admin_auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.admin_auth.ReqTokenLoginAdminDto;
import spring.template.mediasocial.dto.admin_auth.ResTokenLoginAdminDto;
import spring.template.mediasocial.service.login_admin.AdminCredentialService;

@RestController
@RequestMapping("/v1/admin-credential")
@Validated
@RequiredArgsConstructor
public class AdminCredentialController {
    private final AdminCredentialService adminCredentialService;

    @PostMapping("login")
    public ResMessageDto<ResTokenLoginAdminDto> adminLogin(@RequestBody ReqTokenLoginAdminDto request){
        return ResMessageDto.<ResTokenLoginAdminDto>builder()
                .data(adminCredentialService.getLoginAdminToken(request))
                .statusCode(200)
                .build();
    }
    @PostMapping("/login-cookies")
    public ResponseEntity<?> adminLoginHttpOnlyCookies(@RequestBody ReqTokenLoginAdminDto request){
        return adminCredentialService.getLoginAdminTokensAndSetHttpOnlyCookies(request);
    }
}
