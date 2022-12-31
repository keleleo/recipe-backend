package com.dev.backend.controllers.errors;

import com.dev.backend.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler {

    private FieldError err;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        System.out.println("validationException");
        Map<String, String> map = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            map.put(err.getField(), err.getDefaultMessage());
        });

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Argument Not valid", map);
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest req, Throwable ex) {
//        ex.notifyAll();
        return ResponseEntity.ok(

                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage())
        );
    }


}
