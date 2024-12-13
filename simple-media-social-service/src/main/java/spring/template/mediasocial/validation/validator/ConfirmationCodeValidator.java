package spring.template.mediasocial.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.mediasocial.dto.signup.validate_verification_code_2.ReqValidateConfirmationCode;
import spring.template.mediasocial.entity.ConfirmationCodeEntity;
import spring.template.mediasocial.repository.ConfirmationCodeRepository;
import spring.template.mediasocial.validation.annotation.ConfirmationCodeValid;
import spring.template.mediasocial.validation.interfaces.BaseValidator;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConfirmationCodeValidator extends BaseValidator implements ConstraintValidator<ConfirmationCodeValid, ReqValidateConfirmationCode> {
    private final ConfirmationCodeRepository confirmationCodeRepository;

    private static final String NODE_CREDENTIAL_IDENTIFIER = "credentialIdentifier";
    private static final String NODE_CONFIRMATION_CODE = "confirmationCode";

    @Override
    public boolean isValid(ReqValidateConfirmationCode request, ConstraintValidatorContext context) {
        log.info("Start Validating ReqValidateConfirmationCode");
        // check credentialIdentifier & confirmationCode Exist
        if (isConfirmationCodeNotMatch(request, context)) return false;
        // check expired or not
        ConfirmationCodeEntity confirmationCode = confirmationCodeRepository.findByCredentialIdentifierAndCode(request.getCredentialIdentifier(), request.getConfirmationCode());
        return !isConfirmationCodeExpired(context, confirmationCode);
    }

    private boolean isConfirmationCodeNotMatch(ReqValidateConfirmationCode request, ConstraintValidatorContext context) {
        if(!confirmationCodeRepository.existsByCredentialIdentifierAndCode(request.getCredentialIdentifier(), request.getConfirmationCode())){
            buildMessage(context,NODE_CREDENTIAL_IDENTIFIER,"Confirmation code not match");
            return true;
        }
        return false;
    }

    private static boolean isConfirmationCodeExpired(ConstraintValidatorContext context, ConfirmationCodeEntity confirmationCode) {
        if(Instant.now().getEpochSecond() > confirmationCode.getExpirationMillis()) {
            buildMessage(context, NODE_CONFIRMATION_CODE, "Confirmation Code is Expired"  );
            return true;
        }
        return false;
    }
}
