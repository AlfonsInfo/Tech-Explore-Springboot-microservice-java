package spring.template.mediasocial.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.repository.UserSignupRepository;
import spring.template.mediasocial.service.verification_code.ConfirmationCodeService;

@Service
@RequiredArgsConstructor
@Slf4j
public class WhatsappNotificationService  implements NotificationService {
    private final UserSignupRepository userSignupRepository;
    private final ConfirmationCodeService verificationCodeService;

    @Deprecated
    public void sendConfirmationCodeOld(String to) {
        // send whatsapp
    }

    @Override
    public void sendConfirmationCode(String to, UserSignupEntity userSignupEntity) {
        try{
            //send verification
            String verificationCode  = verificationCodeService.createVerificationCode(to);
            log.info("Verification Code : {} send to : {}", verificationCode, to);
            userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE_SUCCESS);
        }catch (Exception e){
            userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE_FAILED);
        }
    }
}
