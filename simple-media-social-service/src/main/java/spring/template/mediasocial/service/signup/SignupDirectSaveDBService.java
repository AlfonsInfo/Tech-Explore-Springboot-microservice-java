package spring.template.mediasocial.service.signup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.signup.ReqInitSignup;
import spring.template.mediasocial.dto.signup.ResInitSignup;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.repository.UserSignupRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupDirectSaveDBService{

    private final UserSignupRepository userSignupRepository;

    public ResInitSignup initSignup(ReqInitSignup request){
        UserSignupEntity userSignupEntity = new UserSignupEntity();
        userSignupEntity.setSignupMethod(request.getSignupMethod());
        switch (request.getSignupMethod()){
            case USING_PHONE -> userSignupEntity.setPhoneNumber(request.getCredentialIdentifier());
            case USING_EMAIL -> userSignupEntity.setEmail(request.getCredentialIdentifier());
        }
        userSignupRepository.save(userSignupEntity);

        return ResInitSignup
                .builder()
                .registrationId(userSignupEntity.getId())
                .build();
    }
}
