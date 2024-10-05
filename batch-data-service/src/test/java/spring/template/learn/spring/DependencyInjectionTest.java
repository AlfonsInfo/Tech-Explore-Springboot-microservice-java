package spring.template.learn.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.template.learn.controller.BrandController;
import spring.template.learn.service.BrandService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class DependencyInjectionTest {

    @Autowired
    private BrandController brandController;

    @Autowired
    private BrandService brandService;

    @Test
    public void testBrandControllerInjection() {
        assertNotNull(brandController, "BrandController should be injected by Spring");
    }

    @Test
    public void testBrandServiceInjection() {
        assertNotNull(brandService, "BrandService should be injected by Spring");
    }
}
