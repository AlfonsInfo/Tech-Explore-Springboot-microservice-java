package spring.template.mediasocial.service.notification;

import org.springframework.stereotype.Service;
import spring.template.mediasocial.entity.UserSignupEntity;

@Service
public class WhatsappNotificationService  implements NotificationService {

    @Deprecated
    public void sendConfirmationCodeOld(String to) {
        // send whatsapp
    }

    @Override
    public void sendConfirmationCode(String to, UserSignupEntity userSignupEntity) {
        try{
            userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE_SUCCESS);
        }catch (Exception e){
            userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE_FAILED);
        }
    }
}
