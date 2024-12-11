package spring.template.mediasocial.dto.signup;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import spring.template.mediasocial.entity.UserSignupEntity;

@Data
public class ReqInitSignup {
    @NotNull
    private UserSignupEntity.SignupMethod signupMethod;
    private String credentialIdentifier;

}
