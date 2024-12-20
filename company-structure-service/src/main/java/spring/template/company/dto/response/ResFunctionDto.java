package spring.template.company.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ResFunctionDto {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long departmentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String departmentName;


}
