package spring.template.learn.validation.validator;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import spring.template.learn.entity.BrandEntity;
import spring.template.learn.repository.BrandRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("sit")
@Slf4j
class BrandIsFoundValidatorTest {


    @Autowired
    private BrandIsFoundValidator brandIsFoundValidator;

    @Autowired
    private BrandRepository brandRepository;

    private BrandEntity brandEntity;


    @BeforeEach
    void setUp() {
        brandEntity = brandRepository.saveAndFlush(BrandEntity.builder().name("Brand 1").build());
        log.info("brandEntity: {}", brandEntity);
    }


    @Test
    void whenBrandIsValid_thenShouldReturnTrue() {
        //when
        var result = brandIsFoundValidator.isValid(brandEntity.getId(), null);
        var resultQuery = brandRepository.existsById(brandEntity.getId());
        //then
        assertThat(result).isTrue();
        assertThat(resultQuery).isTrue();
    }

}