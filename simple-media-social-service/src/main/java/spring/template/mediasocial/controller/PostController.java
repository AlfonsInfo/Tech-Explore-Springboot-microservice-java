package spring.template.mediasocial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.post.ReqCreatePost;
import spring.template.mediasocial.service.post.PostService;

@RestController
@RequestMapping("/v1/posts")
@Validated
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createDepartment(
            @RequestBody ReqCreatePost request
    ) {
        postService.createPost(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.DEPARTMENT_CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

}
