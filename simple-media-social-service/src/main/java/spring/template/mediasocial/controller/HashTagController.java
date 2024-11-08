package spring.template.mediasocial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.hashtag.ReqCreateHashTag;
import spring.template.mediasocial.dto.post.ReqCreatePost;
import spring.template.mediasocial.repository.HashtagRepository;
import spring.template.mediasocial.service.hashtag.HashTagService;

import java.util.List;

@RestController
@RequestMapping("/v1/hash-tags")
@Validated
@RequiredArgsConstructor
public class HashTagController {
     private final HashTagService hashTagService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createTag(
            @RequestBody List<String> tags
    ) {
        hashTagService.create(tags);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.DEPARTMENT_CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

}
