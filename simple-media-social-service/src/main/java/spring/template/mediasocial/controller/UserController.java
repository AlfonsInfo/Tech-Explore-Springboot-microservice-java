package spring.template.mediasocial.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.template.mediasocial.dto.response.ResMessageDto;
import spring.template.mediasocial.dto.user.CreateUserDto;
import spring.template.mediasocial.service.UserService;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResMessageDto<Void> registerUser(
            @RequestBody @Valid CreateUserDto request
    ) {
        // create user
        userService.registerUser(request);
        return ResMessageDto.<Void>builder()
                .message("User created")
                .statusCode(201)
                .build();
    }
}
