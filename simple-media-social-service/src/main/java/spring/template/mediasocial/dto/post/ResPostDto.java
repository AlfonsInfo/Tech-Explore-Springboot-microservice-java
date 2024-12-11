package spring.template.mediasocial.dto.post;

import lombok.Data;
import spring.template.mediasocial.enums.PostEnum;

import java.util.UUID;

@Data
public class ResPostDto {
    private Long id;
    private String userId;
    private String userName;
    private String contentType;
    private String content;
    private String caption;
    private String location;
    private PostEnum status;
    private String createdAt;
    private String totalLike;
    private String totalComment;
}
