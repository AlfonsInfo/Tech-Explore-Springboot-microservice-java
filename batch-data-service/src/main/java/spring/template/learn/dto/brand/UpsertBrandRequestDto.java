package spring.template.learn.dto.brand;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.template.learn.validation.annotation.BrandIsFound;
import spring.template.learn.validation.groups.Update;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertBrandRequestDto {
    @NotNull(groups = Update.class)
    @BrandIsFound(groups = Update.class)
    private Long id;
    @NotBlank
    private String name;
    @NotEmpty
    private String description;
}
