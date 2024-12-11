package spring.template.mediasocial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.dto.ResMessageDto;
import spring.template.mediasocial.dto.post.ResPostDto;
import spring.template.mediasocial.service.hashtag.HashTagService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/hash-tags")
@Validated
@RequiredArgsConstructor
public class HashTagController {
     private final HashTagService hashTagService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createTag(
            @RequestBody Map<String,List<String>> tags
    ) {
        hashTagService.create(tags.get("tags"));
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.HASHTAG_CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<List<String>> getTags() {
        return ResMessageDto.<List<String>>builder()
                .data(hashTagService.getTags())
                .message(MessageResponse.HASHTAG_RETRIEVED)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{tag}/posts")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<List<ResPostDto>> getPostsByTag(
            @PathVariable Long tag
    ) {
        return ResMessageDto.<List<ResPostDto>>builder()
                .data(hashTagService.getPostsByTag(tag))
                .message(MessageResponse.HASHTAG_RETRIEVED)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}
