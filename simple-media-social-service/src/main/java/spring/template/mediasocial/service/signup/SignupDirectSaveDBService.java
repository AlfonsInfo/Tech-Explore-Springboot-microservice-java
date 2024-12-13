package spring.template.mediasocial.service.signup;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.signup.init_1.ReqInitSignup;
import spring.template.mediasocial.dto.signup.init_1.ResInitSignup;
import spring.template.mediasocial.dto.signup.validate_verification_code_2.ReqValidateConfirmationCode;
import spring.template.mediasocial.dto.signup.validate_verification_code_2.ResValidateConfirmationCode;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.repository.ConfirmationCodeRepository;
import spring.template.mediasocial.repository.UserSignupRepository;
import spring.template.mediasocial.service.notification.NotificationService;

@Service
@Slf4j
public class SignupDirectSaveDBService{

    // repository
    private final UserSignupRepository userSignupRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;

    // service
    private final NotificationService emailNotificationService;
    private final NotificationService whatsappNotificationService;

    public SignupDirectSaveDBService(
            UserSignupRepository userSignupRepository,
            ConfirmationCodeRepository confirmationCodeRepository,
            @Qualifier("emailNotificationService")
            NotificationService emailNotificationService,
            @Qualifier("whatsappNotificationService")
            NotificationService whatsappNotificationService) {
        this.userSignupRepository = userSignupRepository;
        this.confirmationCodeRepository = confirmationCodeRepository;
        this.emailNotificationService = emailNotificationService;
        this.whatsappNotificationService = whatsappNotificationService;
    }

    /**
     *
     * @param request : signup method, phoneNumber or email base on signup method
     * @return ResInitSignup
     * process :
     * 1. Save user signup entity with phone number & email
     * 2. send confirmation code to whatsapp / email
     * 3. update signupState
     */
    public ResInitSignup initSignup(ReqInitSignup request){
        UserSignupEntity userSignupEntity = new UserSignupEntity();
        userSignupEntity.setSignupMethod(request.getSignupMethod());
        String credentialIdentifier;
        switch (request.getSignupMethod()){
            case USING_PHONE -> {
                userSignupEntity.setPhoneNumber(request.getCredentialIdentifier());
                whatsappNotificationService.sendConfirmationCode(request.getCredentialIdentifier(),userSignupEntity);
            }
            case USING_EMAIL -> {
                userSignupEntity.setEmail(request.getCredentialIdentifier());
                emailNotificationService.sendConfirmationCode(request.getCredentialIdentifier(),userSignupEntity);
            }
        }
        userSignupRepository.save(userSignupEntity);

        return ResInitSignup
                .builder()
                .signupId(userSignupEntity.getId())
                .credentialIdentifier(request.getCredentialIdentifier())
                .build();
    }


    public ResValidateConfirmationCode validateConfirmationCodeAndUpdateState(ReqValidateConfirmationCode request){
        UserSignupEntity userSignupEntity = userSignupRepository.findByCredentialIdentifier(request.getCredentialIdentifier()).orElseThrow(() -> new EntityNotFoundException("Identifier Not Found"));
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.VERIFICATION_CODE_CONFIRMED);
        userSignupRepository.save(userSignupEntity);
        return ResValidateConfirmationCode
                .builder()
                .build();
    }



}
