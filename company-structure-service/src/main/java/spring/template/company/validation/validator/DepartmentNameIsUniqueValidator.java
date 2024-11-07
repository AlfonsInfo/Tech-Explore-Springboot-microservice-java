package spring.template.company.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.company.repository.DepartmentRepository;
import spring.template.company.validation.annotation.DepartmentIdIsFound;
import spring.template.company.validation.annotation.DepartmentNameIsUnique;

@Component
@RequiredArgsConstructor
@Slf4j
public class DepartmentNameIsUniqueValidator implements ConstraintValidator<DepartmentNameIsUnique, String> {

    private final DepartmentRepository departmentRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        log.info("Validating department name: {}", name);
        if(name == null){return true;}
        return departmentRepository.existsByName(name);
    }

}
