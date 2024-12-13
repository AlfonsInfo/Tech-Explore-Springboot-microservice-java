package spring.template.mediasocial.dto.signup.validate_verification_code_2;

import lombok.Data;
import spring.template.mediasocial.validation.annotation.ConfirmationCodeValid;

@Data
@ConfirmationCodeValid
public class ReqValidateConfirmationCode {
    private String credentialIdentifier;
    private String confirmationCode;
}
