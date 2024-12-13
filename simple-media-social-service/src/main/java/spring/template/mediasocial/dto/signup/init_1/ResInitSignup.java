package spring.template.mediasocial.dto.signup.init_1;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResInitSignup {
    private Long signupId;
    private String credentialIdentifier;
}
