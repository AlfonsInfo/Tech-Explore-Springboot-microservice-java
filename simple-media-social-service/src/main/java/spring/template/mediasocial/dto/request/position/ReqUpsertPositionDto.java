package spring.template.mediasocial.dto.request.position;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import spring.template.mediasocial.validation.annotation.DepartmentIdIsFound;

@Data
public class ReqUpsertPositionDto {
        @NotBlank
        private String name;
        private String description;
        @DepartmentIdIsFound
        private Long departmentId;
}
