package spring.template.mediasocial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.like.ReqCreateLikeDto;
import spring.template.mediasocial.dto.post.ReqCreatePost;
import spring.template.mediasocial.service.like.LikeService;

@RestController
@RequestMapping("/v1/likes")
@Validated
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createPost(
            @RequestBody ReqCreateLikeDto request
    ) {
        likeService.createLike(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.POST_CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

}
