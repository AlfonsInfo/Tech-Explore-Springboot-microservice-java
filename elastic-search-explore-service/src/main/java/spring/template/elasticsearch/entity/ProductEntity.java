package spring.template.elasticsearch.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
@Data
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
}


