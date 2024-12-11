package spring.template.mediasocial.controller.signup;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.signup.ReqInitSignup;
import spring.template.mediasocial.dto.signup.ResInitSignup;
import spring.template.mediasocial.service.signup.SignupDirectSaveDBService;

@RestController
@RequestMapping("/v3/signup")
@Validated
public class SignupV3Controller {

    private final SignupDirectSaveDBService signupService;

    @Autowired
    public SignupV3Controller(
            SignupDirectSaveDBService signupService
    ) {
        this.signupService = signupService;
    }


    @PostMapping("/init")
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<ResInitSignup> initSignup(@RequestBody @Valid ReqInitSignup request) {
        return ResMessageDto.<ResInitSignup>builder()
                .data(signupService.initSignup(request))
                .message(MessageResponse.REGISTRATION_INIT_SUCCESSFULLY)
                .statusCode(201)
                .build();
    }
}
