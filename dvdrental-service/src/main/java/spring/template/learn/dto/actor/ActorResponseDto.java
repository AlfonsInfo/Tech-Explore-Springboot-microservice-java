package spring.template.learn.dto.actor;

import lombok.Data;

@Data
public class ActorResponseDto {
    private Long actorId;
    private String firstName;
    private String lastName;
}
