package spring.template.learn.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.learn.repository.BrandRepository;
import spring.template.learn.repository.DiscountRepository;
import spring.template.learn.validation.annotation.DiscountCodeIsUnique;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class DiscountCodeIsUniqueValidator
        implements ConstraintValidator<DiscountCodeIsUnique, String> {

    private final DiscountRepository discountRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("Validating discount code: {}", value);
        if(Objects.isNull(value)) {return true;}
        boolean result = discountRepository.existsByCodeAndIsActive(value,true);
        log.info("Discount code: {} is unique: {}", value, result);
        return !result;
    }
}
