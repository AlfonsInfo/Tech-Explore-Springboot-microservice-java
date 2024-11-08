package spring.template.mediasocial.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.template.mediasocial.validation.validator.ValidUUIDValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValidUUIDValidator.class)
@Target({ FIELD,  PARAMETER})
public @interface ValidUUID {
    String message() default "Invalid UUID";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
