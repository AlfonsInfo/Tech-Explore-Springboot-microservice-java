package spring.template.mediasocial.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.template.mediasocial.validation.annotation.ValidUUID;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidUUIDValidator implements ConstraintValidator<ValidUUID, String> {


    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        try{
            UUID.fromString(id);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

}
