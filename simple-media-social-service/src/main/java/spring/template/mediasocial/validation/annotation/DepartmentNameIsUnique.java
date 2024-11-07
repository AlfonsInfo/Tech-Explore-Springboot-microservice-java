package spring.template.mediasocial.validation.annotation;

import jakarta.validation.Constraint;
import spring.template.mediasocial.validation.validator.DepartmentNameIsUniqueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentNameIsUniqueValidator.class)
@Target({ TYPE, FIELD, PARAMETER })
public @interface DepartmentNameIsUnique {
    String message() default "Department name is not unique";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
