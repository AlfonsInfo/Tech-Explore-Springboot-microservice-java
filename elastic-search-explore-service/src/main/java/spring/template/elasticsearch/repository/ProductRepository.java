package spring.template.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import spring.template.elasticsearch.entity.ProductEntity;

public interface ProductRepository extends ElasticsearchRepository<ProductEntity, Long> {

}
