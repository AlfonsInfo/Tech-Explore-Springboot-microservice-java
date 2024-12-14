package spring.template.mediasocial.dto.signup;

import lombok.Builder;
import lombok.Data;
import spring.template.mediasocial.entity.UserSignupEntity;

@Data
@Builder
public class ResSignupPatchDto {
    private Long signupId;
    private UserSignupEntity.SignupState signupState;
}
