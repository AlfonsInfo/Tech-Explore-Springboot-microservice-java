package spring.template.mediasocial.dto.request.function;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import spring.template.mediasocial.validation.annotation.DepartmentIdIsFound;

@Data
public class ReqUpsertFunctionDto {
        @NotBlank
        private String name;
        private String description;
        @DepartmentIdIsFound
        private Long departmentId;
}
