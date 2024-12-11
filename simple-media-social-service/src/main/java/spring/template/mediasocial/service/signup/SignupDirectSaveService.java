package spring.template.mediasocial.service.signup;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.user.ReqCreateUserDto;
import spring.template.mediasocial.entity.UserEntity;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.mapper.UserMapper;
import spring.template.mediasocial.repository.UserRepository;
import spring.template.mediasocial.service.notification.NotificationService;

@Service
@Slf4j
@Deprecated
public class SignupDirectSaveService implements SignupService {
    //Repository
    private final UserRepository userRepository;
    //Validation
    private final SignupValidationService signupValidationService;
    //(Notification)
    private final NotificationService notificationService;
    private final NotificationService whatsappNotificationService;
    //Mapping
    private final UserMapper userMapper;

    public SignupDirectSaveService(
            UserRepository userRepository,
            SignupValidationService signupValidationService,
            @Qualifier("emailNotificationService") NotificationService emailNotificationService,
            @Qualifier("whatsappNotificationService") NotificationService whatsappNotificationService,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.signupValidationService = signupValidationService;
        this.notificationService = emailNotificationService;
        this.whatsappNotificationService = whatsappNotificationService;
        this.userMapper = userMapper;
    }

    @Transactional
    public void signUp(ReqCreateUserDto request){
//        //Get signup method
//        SignupMethod signupMethod = getSignupMethod(request.getMobileNumberOrEmail());
//        //Validation
//        validateEmailOrPhone(request, signupMethod);
//        //Map to entity
//        UserEntity userEntity = mapIntoUserEntity(request, signupMethod);
//        //send verification
//        sendVerification(request, signupMethod);
//        //Save to database
//        userRepository.save(userEntity);
    }

    @Override
    public void confirm(String code) {

    }

    private void sendVerification(ReqCreateUserDto request, UserSignupEntity.SignupMethod signupMethod) {
        if(signupMethod == UserSignupEntity.SignupMethod.USING_EMAIL){
            notificationService.sendConfirmationCode(request.getMobileNumberOrEmail());
        }else {
            whatsappNotificationService.sendConfirmationCode(request.getMobileNumberOrEmail());
        }
    }

    private static UserEntity mapIntoUserEntity(ReqCreateUserDto request, UserSignupEntity.SignupMethod signupMethod) {
//        UserCredentialEntity userCredentialEntity = new UserCredentialEntity();
//        userCredentialEntity.setPassword(request.getPassword());
//        userCredentialEntity.setEmail(request.getUserName());
//        userCredentialEntity.setSignupMethod(signupMethod);
//        if(signupMethod == SignupMethod.USING_PHONE){
//            userCredentialEntity.setPhone(request.getMobileNumberOrEmail());
//        }else {
//            userCredentialEntity.setEmail(request.getMobileNumberOrEmail());
//        }
//        return userCredentialEntity;
        return new UserEntity();
    }

    private void validateEmailOrPhone(ReqCreateUserDto request, UserSignupEntity.SignupMethod signupMethod) {
//        if (signupMethod == SignupMethod.USING_EMAIL) {
//            signupValidationService.isEmailValid(request.getMobileNumberOrEmail());
//        } else {
//            signupValidationService.isPhoneValid(request.getMobileNumberOrEmail());
//        }
    }

    private UserSignupEntity.SignupMethod getSignupMethod(String emailOrMethod) {
        if (emailOrMethod.contains("@")) {
            return UserSignupEntity.SignupMethod.USING_EMAIL;
        } else {
            return UserSignupEntity.SignupMethod.USING_PHONE;
        }

    }


}
