package spring.template.learn.dto.category;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long categoryId;
    private String name;
    private String lastUpdate;
}
