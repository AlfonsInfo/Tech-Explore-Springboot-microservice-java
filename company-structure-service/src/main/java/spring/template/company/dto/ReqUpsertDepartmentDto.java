package spring.template.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqUpsertDepartmentDto {
        private String id;
        @NotBlank
        private String name;
        private String description;
}
