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

@Component
@RequiredArgsConstructor
@Slf4j
public class ConfirmationCodeValidator extends BaseValidator implements ConstraintValidator<ConfirmationCodeValid, ReqValidateConfirmationCode> {
    private final ConfirmationCodeRepository confirmationCodeRepository;


    @Override
    public boolean isValid(ReqValidateConfirmationCode request, ConstraintValidatorContext context) {
        log.info("Start Validating ReqValidateConfirmationCode");
        // check credentialIdentifier & confirmationCode Exist
        if(!confirmationCodeRepository.existsByCredentialIdentifierAndCode(request.getCredentialIdentifier(), request.getConfirmationCode())){

            return false;
        }
        // check expired or not
        ConfirmationCodeEntity confirmationCode = confirmationCodeRepository.findByCredentialIdentifierAndCode(request.getCredentialIdentifier(), request.getConfirmationCode());
        if(System.currentTimeMillis() > confirmationCode.getExpirationMillis()) return false;
        // count fail attempt -> if 5 lock
        return true;
    }
}
