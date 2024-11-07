package spring.template.mediasocial.dto.request.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import spring.template.mediasocial.validation.annotation.DepartmentNameIsUnique;

@Data
public class ReqCreateDepartmentDto {
        @NotBlank
        @DepartmentNameIsUnique(message = "Department name is not unique")
        private String name;
        private String description;
}
