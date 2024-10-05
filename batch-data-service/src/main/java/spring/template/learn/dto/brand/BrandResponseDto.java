package spring.template.learn.dto.brand;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandResponseDto {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
}
