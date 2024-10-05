package spring.template.learn.dto.discount;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.template.learn.validation.annotation.DiscountCodeIsUnique;
import spring.template.learn.validation.groups.Create;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertDiscountDto {
    @NotNull
    @Size(min = 1, max = 100)
    @DiscountCodeIsUnique(groups = {Create.class})
    private String code;
    @NotNull
    private Long productId;
    @NotNull
    @Size(min = 1, max = 100)
    private Integer percentage;
    @NotNull
    private Timestamp validFrom;
    @NotNull
    private Timestamp validTo;
}
