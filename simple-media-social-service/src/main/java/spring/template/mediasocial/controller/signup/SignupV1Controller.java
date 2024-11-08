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

//Signup directly save on db
@RestController
@RequestMapping("/v1/signup")
@Validated
public class SignupV1Controller {

    private final SignupService signupService;

    @Autowired
    public SignupV1Controller(
            @Qualifier("signupDirectSaveService")
            SignupService signupService
    ) {
        this.signupService = signupService;
    }


    @PostMapping("/input-data")
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

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> verifyUser(
            @RequestBody @Valid String request
    ) {
        // create user
        signupService.confirm(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.USER_CREATED)
                .statusCode(201)
                .build();
    }
}
