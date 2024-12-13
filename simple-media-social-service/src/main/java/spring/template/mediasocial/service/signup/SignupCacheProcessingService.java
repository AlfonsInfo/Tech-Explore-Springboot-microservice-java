package spring.template.mediasocial.service.signup;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.user.ReqCreateUserDto;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.mapper.UserMapper;
import spring.template.mediasocial.repository.UserCredentialRepository;
import spring.template.mediasocial.service.notification.NotificationService;
import spring.template.mediasocial.utility.RegexUtil;

@Service
@Slf4j
@Deprecated
public class SignupCacheProcessingService
implements SignupService{

    //Repository
    private final UserCredentialRepository userCredentialRepository;
    //Validation
    private final RegexUtil signupValidationService;
    //(Notification)
    private final NotificationService notificationService;

    private final NotificationService whatsappNotificationService;
    //Mapping
    private final UserMapper userMapper;

    public SignupCacheProcessingService(
            UserCredentialRepository userCredentialRepository,
            RegexUtil signupValidationService,
            @Qualifier("emailNotificationService")
            NotificationService emailNotificationService,
            @Qualifier("whatsappNotificationService")
            NotificationService whatsappNotificationService,
            UserMapper userMapper
    ) {
        this.userCredentialRepository = userCredentialRepository;
        this.signupValidationService = signupValidationService;
        this.notificationService = emailNotificationService;
        this.whatsappNotificationService = whatsappNotificationService;
        this.userMapper = userMapper;
    }


    @Transactional
    public void signUp(ReqCreateUserDto request){
//        //Get signup method
//        UserSignupEntity.SignupMethod signupMethod = getSignupMethod(request.getMobileNumberOrEmail());
//        //Validation
//        validateEmailOrPhone(request, signupMethod);
//        //Map to entity
//        UserCredentialEntity userEntity = mapIntoUserEntity(request, signupMethod);
//        //send verification
//        sendVerification(request, signupMethod);
//        //Save to database
//        userCredentialRepository.save(userEntity);
    }

    @Override
    public void confirm(String code) {

    }

    private void sendVerification(ReqCreateUserDto request, UserSignupEntity.SignupMethod signupMethod) {
        if(signupMethod == UserSignupEntity.SignupMethod.USING_EMAIL){
            notificationService.sendConfirmationCodeOld(request.getMobileNumberOrEmail());
        }else {
            whatsappNotificationService.sendConfirmationCodeOld(request.getMobileNumberOrEmail());
        }
    }

    private static UserEntity mapIntoUserEntity(ReqCreateUserDto request, UserSignupEntity.SignupMethod signupMethod) {
        UserEntity userEntity = new UserEntity();
//        userEntity.setPassword(request.getPassword());
//        userEntity.setUsername(request.getFullName());
//        userEntity.setEmail(request.getUserName());
//        userEntity.setSignupMethod(signupMethod);
//        if(signupMethod == SignupMethod.USING_PHONE){
//            userEntity.setPhone(request.getMobileNumberOrEmail());
//        }else {
//            userEntity.setEmail(request.getMobileNumberOrEmail());
//        }
        return userEntity;
    }

    private void validateEmailOrPhone(ReqCreateUserDto request, UserSignupEntity.SignupMethod signupMethod) {
        if (signupMethod == UserSignupEntity.SignupMethod.USING_EMAIL) {
            signupValidationService.isEmailValid(request.getMobileNumberOrEmail());
        } else {
            signupValidationService.isPhoneValid(request.getMobileNumberOrEmail());
        }
    }

    private UserSignupEntity.SignupMethod getSignupMethod(String emailOrMethod) {
        if (emailOrMethod.contains("@")) {
            return UserSignupEntity.SignupMethod.USING_EMAIL;
        } else {
            return UserSignupEntity.SignupMethod.USING_PHONE;
        }
    }


}
