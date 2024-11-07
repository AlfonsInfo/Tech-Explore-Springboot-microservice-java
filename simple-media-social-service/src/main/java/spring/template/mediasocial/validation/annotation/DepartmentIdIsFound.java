package spring.template.mediasocial.validation.annotation;

import jakarta.validation.Constraint;
import spring.template.mediasocial.constant.MessageValidator;
import spring.template.mediasocial.validation.validator.DepartmentIdIsFoundValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentIdIsFoundValidator.class)
@Target({ FIELD,  PARAMETER})
public @interface DepartmentIdIsFound {
    String message() default MessageValidator.DEPARTMENT_ID_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
