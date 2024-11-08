package spring.template.mediasocial.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.template.mediasocial.constant.HeaderConstant;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.user.ResTotalPosts;
import spring.template.mediasocial.service.user.UserService;
import spring.template.mediasocial.validation.annotation.ValidUUID;

@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpServletRequest request;


    @GetMapping("/total-posts")
    public ResMessageDto<ResTotalPosts> getTotalPosts() {
        String loginIdUser = request.getHeader(HeaderConstant.ID_USER);
        ResTotalPosts totalPost = userService.getUserTotalPosts(loginIdUser);
        return ResMessageDto.<ResTotalPosts>builder()
                .data(totalPost)
                .statusCode(200)
                .build();
    }

    @GetMapping("/{userId}/total-posts")
    public ResMessageDto<ResTotalPosts> getUserTotalPosts(
            @ValidUUID @PathVariable("userId") String userId
    ) {
            ResTotalPosts totalPost =  userService.getUserTotalPosts(userId);
            return ResMessageDto.<ResTotalPosts>builder()
                    .data(totalPost)
                    .statusCode(200)
                    .build();
    }


}
