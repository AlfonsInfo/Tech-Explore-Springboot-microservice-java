package spring.template.mediasocial.dto.like;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProjectionLikeCount {
    private UUID postId;
    private Integer count;
}
