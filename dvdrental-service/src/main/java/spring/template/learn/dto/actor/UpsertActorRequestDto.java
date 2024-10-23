package spring.template.learn.dto.actor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpsertActorRequestDto {
    private Long actorId;
    @NotBlank
    private String firstName;
    @NotEmpty
    private String lastName;
}
