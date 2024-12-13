package spring.template.mediasocial.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.mediasocial.dto.signup.init_1.ReqInitSignup;
import spring.template.mediasocial.repository.ConfirmationCodeRepository;
import spring.template.mediasocial.utility.RegexUtil;
import spring.template.mediasocial.service.user.UserCredentialService;
import spring.template.mediasocial.validation.annotation.InitSignupIsValid;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitSignupValidator implements ConstraintValidator<InitSignupIsValid, ReqInitSignup> {
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final UserCredentialService userCredentialService;
    private final RegexUtil regexUtil;

    private static final String NODE_CREDENTIAL_IDENTIFIER = "credentialIdentifier";

    @Override
    public boolean isValid(ReqInitSignup request, ConstraintValidatorContext context) {
        if(request.getCredentialIdentifier() == null) return true;
        // check availability
        if(userCredentialService.isCredentialIdentifierNotAvailableForNewAccount(request.getCredentialIdentifier())) {
            buildMessage(context,NODE_CREDENTIAL_IDENTIFIER,"Email or Phone not available for new account");
            return false;
        };
        // check blacklisted
        if(userCredentialService.isCredentialIdentifierBlacklisted(request.getCredentialIdentifier())) {
            buildMessage(context, NODE_CREDENTIAL_IDENTIFIER, "Email or Phone is blacklisted");
            return false;
        };
        //format email/phone number
        return (regexUtil.isEmailValid(request.getCredentialIdentifier()) || regexUtil.isPhoneValid(request.getCredentialIdentifier()));
    }

    private void buildMessage(ConstraintValidatorContext context,String propertyNode, String message){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(propertyNode).addConstraintViolation();
    }
}
