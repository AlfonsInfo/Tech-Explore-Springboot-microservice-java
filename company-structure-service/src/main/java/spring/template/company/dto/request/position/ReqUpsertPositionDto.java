package spring.template.company.dto.request.position;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import spring.template.company.validation.annotation.DepartmentIdIsFound;

@Data
public class ReqUpsertPositionDto {
        @NotBlank
        private String name;
        private String description;
        @DepartmentIdIsFound
        private Long departmentId;
}
