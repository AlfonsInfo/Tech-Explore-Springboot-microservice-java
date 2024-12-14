package spring.template.mediasocial.validation.interfaces;

import jakarta.validation.ConstraintValidatorContext;

public class BaseValidator {
    protected static void buildMessage(ConstraintValidatorContext context, String propertyNode, String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(propertyNode).addConstraintViolation();
    }
}
