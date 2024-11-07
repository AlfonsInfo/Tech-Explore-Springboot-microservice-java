package spring.template.mediasocial.configuration.errorhandling;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import spring.template.mediasocial.dto.response.ResMessageDto;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResMessageDto<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ResMessageDto.<Void>builder()
                .error(e.getMessage())
                        .errors(
                                e.getBindingResult()
                                        .getFieldErrors()
                                        .stream()
                                        .map(this::buildFieldErrorMessage)
                                        .toList()
                        )
                .statusCode(400)
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResMessageDto<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ResMessageDto.<Void>builder()
                .error(e.getMessage())
                .statusCode(400)
                .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResMessageDto<Void>> handleResponseStatusException(ResponseStatusException e) {
        log.error("ResponseStatusException: {}", e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(ResMessageDto.<Void>builder()
                .error(e.getMessage())
                .statusCode(e.getStatusCode().value())
                .build());
    }

    private String buildFieldErrorMessage(FieldError fieldError) {
        return fieldError.getField() + " "+ fieldError.getDefaultMessage();
    }

}
