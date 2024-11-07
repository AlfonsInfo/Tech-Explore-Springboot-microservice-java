package spring.template.company.dto.request.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqUpdateDepartmentDto {
        @NotBlank
        private String name;
        private String description;
}
