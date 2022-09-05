package com.propify.challenge.handler;

import com.propify.challenge.exception.NotValidException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(Errors errors) {
        FieldError fieldErrors = errors.getFieldError();
        String message = nonNull(fieldErrors) ? fieldErrors.getDefaultMessage() : null;
        return ResponseEntity.status(BAD_REQUEST).body(message);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<String> handleAllException(Exception ex) {
        return ResponseEntity.status(BAD_REQUEST).body(ex.getMessage());
    }

}