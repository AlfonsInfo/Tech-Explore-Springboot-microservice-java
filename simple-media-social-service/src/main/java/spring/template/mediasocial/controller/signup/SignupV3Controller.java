package spring.template.mediasocial.controller.signup;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.signup.ReqSignupPatchDto;
import spring.template.mediasocial.dto.signup.init_1.ReqInitSignup;
import spring.template.mediasocial.dto.signup.init_1.ResInitSignup;
import spring.template.mediasocial.dto.signup.validate_verification_code_2.ReqValidateConfirmationCode;
import spring.template.mediasocial.dto.signup.ResSignupPatchDto;
import spring.template.mediasocial.service.signup.SignupDirectSaveDBService;
import spring.template.mediasocial.validation.annotation.ConfirmationCodeValid;
import spring.template.mediasocial.validation.annotation.InitSignupIsValid;

@RestController
@RequestMapping("/v3/signups")
@Validated
public class SignupV3Controller {

    private final SignupDirectSaveDBService signupService;

    @Autowired
    public SignupV3Controller(
            SignupDirectSaveDBService signupService
    ) {
        this.signupService = signupService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<ResInitSignup> initSignup(@RequestBody @Valid @InitSignupIsValid ReqInitSignup request) {
        return ResMessageDto.<ResInitSignup>builder()
                .data(signupService.initSignup(request))
                .message(MessageResponse.SIGNUP_INIT_SUCCESSFULLY)
                .statusCode(201)
                .build();
    }

    /**
     *
     * @param request (confirmation code) and credentialIdentifier
     * @return
     * step :
     * 1. validate confirmation code using bean validation
     * 2. update state
     */
    @PatchMapping("/confirmation-code")
    public ResMessageDto<ResSignupPatchDto> validateConfirmationCodeAndUpdateState(
            @RequestBody @ConfirmationCodeValid ReqValidateConfirmationCode request
    ){
        return ResMessageDto.<ResSignupPatchDto>builder()
                .data(signupService.validateConfirmationCodeAndUpdateState(request))
                .statusCode(200)
                .build();
    }

    @PatchMapping("/personals")
    public ResMessageDto<ResSignupPatchDto> validatePersonalDataAndUpdateState(
            @RequestBody ReqSignupPatchDto request
    ){
        return ResMessageDto
                .<ResSignupPatchDto>builder()
                .data(signupService.validatePersonalDataAndUpdateState(request))
                .build();
    }
}
