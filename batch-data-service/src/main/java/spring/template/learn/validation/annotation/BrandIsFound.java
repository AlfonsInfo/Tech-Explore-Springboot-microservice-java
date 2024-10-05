package spring.template.learn.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.template.learn.validation.validator.BrandIsFoundValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrandIsFoundValidator.class)
public @interface BrandIsFound{
    String message() default "Brand is not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}