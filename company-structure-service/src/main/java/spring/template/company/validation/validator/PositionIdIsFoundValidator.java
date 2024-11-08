package spring.template.company.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.company.repository.DepartmentRepository;
import spring.template.company.repository.PositionRepository;
import spring.template.company.validation.annotation.DepartmentIdIsFound;
import spring.template.company.validation.annotation.PositionIdIsFound;

@Component
@RequiredArgsConstructor
@Slf4j
public class PositionIdIsFoundValidator implements ConstraintValidator<PositionIdIsFound, Long> {

    private final PositionRepository positionRepository;


    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        log.info("Validating position id: {}", id);
        if(id == null){return true;}
        return positionRepository.existsById(id);
    }

}
