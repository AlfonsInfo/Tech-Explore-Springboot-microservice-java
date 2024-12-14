package spring.template.mediasocial.dto.admin_auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResTokenLoginAdminDto {
    private String tokens;
}