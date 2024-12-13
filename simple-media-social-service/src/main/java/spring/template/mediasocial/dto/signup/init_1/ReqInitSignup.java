package spring.template.mediasocial.dto.signup.init_1;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import spring.template.mediasocial.entity.UserSignupEntity;

@Data
public class ReqInitSignup {
    @NotNull
    private UserSignupEntity.SignupMethod signupMethod;
    @NotNull
    private String credentialIdentifier;
}
