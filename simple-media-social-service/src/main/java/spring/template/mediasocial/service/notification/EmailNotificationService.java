package spring.template.mediasocial.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.repository.UserSignupRepository;
import spring.template.mediasocial.service.verification_code.VerificationCodeService;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final UserSignupRepository userSignupRepository;

    private final VerificationCodeService verificationCodeService;

    @Deprecated
    public void sendConfirmationCodeOld(String to) {
        // send email
    }

    @Override
    public void sendConfirmationCode(String to, UserSignupEntity userSignupEntity) {
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE);
        userSignupRepository.save(userSignupEntity);
        try{
            //send verification
            String verificationCode  = verificationCodeService.createVerificationCode(to);
            log.info("Verification Code : {} send to : {}", verificationCode, to);
            userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE_SUCCESS);
            userSignupRepository.save(userSignupEntity);
        }catch (Exception e){
            userSignupEntity.setSignupState(UserSignupEntity.SignupState.SEND_VERIFICATION_CODE_FAILED);
            userSignupRepository.save(userSignupEntity);
        }
    }
}
