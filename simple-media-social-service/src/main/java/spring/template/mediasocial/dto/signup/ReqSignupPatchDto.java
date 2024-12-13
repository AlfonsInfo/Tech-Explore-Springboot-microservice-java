package spring.template.mediasocial.dto.signup;

import lombok.Data;
import spring.template.mediasocial.entity.UserSignupEntity;

@Data
public class ReqSignupPatchDto {
    private UserSignupEntity.SignupState signupState;
    private Long signupId;
    private String name;
    private String password;
    private String dateOfBirth;
    private String username;
}
