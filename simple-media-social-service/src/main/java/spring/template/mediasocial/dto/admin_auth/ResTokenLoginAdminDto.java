package spring.template.mediasocial.dto.admin_auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResTokenLoginAdminDto {
    private String tokens;
}
