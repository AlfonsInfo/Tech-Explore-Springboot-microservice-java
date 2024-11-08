package spring.template.mediasocial.dto.post;

import lombok.Data;

import java.util.List;


@Data
public class ReqCreatePost {
    private String content;
    private String contentType;
    private String caption;
    private String location;
    private List<String> postHashtags;
    private List<String> userTags;
    private String status;
}
