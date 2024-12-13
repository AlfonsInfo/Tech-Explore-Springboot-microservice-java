package spring.template.mediasocial.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.template.mediasocial.validation.validator.ConfirmationCodeValidator;
import spring.template.mediasocial.validation.validator.InitSignupValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InitSignupValidator.class)
@Target({ FIELD,  PARAMETER, TYPE})
public @interface InitSignupIsValid {
    String message() default "ReqInitSignup is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
