package spring.template.learn.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.template.learn.constant.ErrorMessages;
import spring.template.learn.validation.validator.DiscountCodeIsUniqueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiscountCodeIsUniqueValidator.class)
public @interface DiscountCodeIsUnique {
    String message() default ErrorMessages.DISCOUNT_NOT_UNIQUE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}