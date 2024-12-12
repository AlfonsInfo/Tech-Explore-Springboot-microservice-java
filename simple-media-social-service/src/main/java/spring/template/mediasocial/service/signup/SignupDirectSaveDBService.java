package spring.template.mediasocial.service.signup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.signup.ReqInitSignup;
import spring.template.mediasocial.dto.signup.ResInitSignup;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.repository.UserSignupRepository;
import spring.template.mediasocial.service.notification.NotificationService;

@Service
@Slf4j
public class SignupDirectSaveDBService{

    private final UserSignupRepository userSignupRepository;
    private final NotificationService emailNotificationService;
    private final NotificationService whatsappNotificationService;

    public SignupDirectSaveDBService(
            UserSignupRepository userSignupRepository,
            @Qualifier("emailNotificationService")
            NotificationService emailNotificationService,
            @Qualifier("whatsappNotificationService")
            NotificationService whatsappNotificationService) {
        this.userSignupRepository = userSignupRepository;
        this.emailNotificationService = emailNotificationService;
        this.whatsappNotificationService = whatsappNotificationService;
    }

    /**
     *
     * @param request
     * @return ResInitSignup
     * process :
     * 1. Save user signup entity with phone number & email
     * 2. send confirmation code to whatsapp / email
     * 3. update signupState
     */
    public ResInitSignup initSignup(ReqInitSignup request){
        UserSignupEntity userSignupEntity = new UserSignupEntity();
        userSignupEntity.setSignupMethod(request.getSignupMethod());
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
                .registrationId(userSignupEntity.getId())
                .build();
    }



}
