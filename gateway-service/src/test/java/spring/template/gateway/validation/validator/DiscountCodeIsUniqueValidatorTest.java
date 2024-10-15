package spring.template.gateway.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.template.gateway.repository.DiscountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



@Slf4j
@ExtendWith(MockitoExtension.class)
class DiscountCodeIsUniqueValidatorTest {


    private DiscountCodeIsUniqueValidator validator;

    @Mock
    private DiscountRepository discountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new DiscountCodeIsUniqueValidator(discountRepository);
    }

        @Test
        void givenDiscountCode_whenDiscountCodeIsUnique_thenShouldReturnTrue() {
            //given
            String discountCode = "DISCOUNT_CODE";
            //when
            when(discountRepository.existsByCodeAndIsActive(discountCode, true)).thenReturn(false);
            var result = validator.isValid(discountCode, null);
            //then
            assertTrue(result);
        }

        @Test
        void givenDiscountCode_whenDiscountCodeIsNotUnique_thenShouldReturnFalse() {
            //given
            String discountCode = "DISCOUNT_CODE";
            //when
            when(discountRepository.existsByCodeAndIsActive(discountCode, true)).thenReturn(true);
            var result = validator.isValid(discountCode, null);
            //then
            assertFalse(result);
        }
}