package spring.template.elasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.template.elasticsearch.entity.ProductEntity;
import spring.template.elasticsearch.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public Iterable<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
