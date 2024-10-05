package spring.template.learn.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.learn.repository.BrandRepository;
import spring.template.learn.validation.annotation.BrandIsFound;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class BrandIsFoundValidator implements ConstraintValidator<BrandIsFound, Long> {

    private final BrandRepository brandRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        log.info("Validating brand id: {}", value);
        if(Objects.isNull(value)) {return true;}
        boolean result = brandRepository.existsById(value);
        log.info("Brand id: {} is found: {}", value, result);
        return result;
    }
}
