package spring.template.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.template.elasticsearch.entity.ProductEntity;
import spring.template.elasticsearch.service.ProductService;

@RequestMapping
@RestController("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public Iterable<ProductEntity> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

}
