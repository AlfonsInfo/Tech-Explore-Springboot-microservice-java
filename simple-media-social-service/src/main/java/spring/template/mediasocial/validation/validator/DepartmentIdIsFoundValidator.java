package spring.template.mediasocial.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.mediasocial.validation.annotation.DepartmentIdIsFound;

@Component
@RequiredArgsConstructor
@Slf4j
public class DepartmentIdIsFoundValidator implements ConstraintValidator<DepartmentIdIsFound, Long> {

    private final DepartmentRepository departmentRepository;


    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        log.info("Validating department id: {}", id);
        if(id == null){return true;}
        return departmentRepository.existsById(id);
    }

}
