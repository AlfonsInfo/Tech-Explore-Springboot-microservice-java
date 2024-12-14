package spring.template.mediasocial.dto.admin_auth;

import lombok.Data;

@Data
public class ReqTokenLoginAdminDto {
    private String email;
    private String password;
}
