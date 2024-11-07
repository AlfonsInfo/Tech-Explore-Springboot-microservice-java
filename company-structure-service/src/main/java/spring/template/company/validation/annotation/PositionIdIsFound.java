package spring.template.company.validation.annotation;

import jakarta.validation.Constraint;
import spring.template.company.constant.MessageResponse;
import spring.template.company.constant.MessageValidator;
import spring.template.company.validation.validator.DepartmentIdIsFoundValidator;
import spring.template.company.validation.validator.PositionIdIsFoundValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PositionIdIsFoundValidator.class)
@Target({ FIELD,  PARAMETER})
public @interface PositionIdIsFound {
    String message() default MessageValidator.POSITION_ID_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
