package spring.template.mediasocial.dto.post;

import lombok.Data;


@Data
public class ReqCreatePost {
    private String content;
    private String mediaType;
    private String caption;
    private String location;
    private String tags;
    private String status;
}
