package spring.template.company.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import spring.template.company.validation.annotation.DepartmentNameIsUnique;

@Data
public class ReqUpdateDepartmentDto {
        @NotBlank
        private String name;
        private String description;
}
