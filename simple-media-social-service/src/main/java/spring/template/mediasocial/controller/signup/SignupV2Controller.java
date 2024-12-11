package spring.template.mediasocial.controller.signup;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.user.ReqCreateUserDto;
import spring.template.mediasocial.service.signup.SignupService;

//this is not restful api, because not based on resource
// but base on action
//Sign up by step-step saving on redis, then save on db
@RestController
@RequestMapping("/v2/signup")
@Validated
@Deprecated
public class SignupV2Controller {

    private final SignupService signupService;

    @Autowired
    public SignupV2Controller(
            @Qualifier("signupCacheProcessingService")
            SignupService signupService
    ) {
        this.signupService = signupService;
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> registerUser(
            @RequestBody @Valid ReqCreateUserDto request
    ) {
        // create user
        signupService.signUp(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.USER_CREATED)
                .statusCode(201)
                .build();
    }
}
