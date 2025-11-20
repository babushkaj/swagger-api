package com.katsiankou.swagger.controller.handler;

import com.katsiankou.models.error.ErrorMessage;
import com.katsiankou.swagger.controller.CurrencyController;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = CurrencyController.class)
public class CurrencyControllerExceptionHandler {

    private static final String FEIGN_INTERNAL_SERVER_ERROR = "A problem occurred while calling NBRB API. Please, try again.";
    private static final String INTERNAL_SERVER_ERROR = "Internal server error. Please, try again.";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException e) {
        var message = e.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining("; "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorMessage(message));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorMessage> handleFeignException(FeignException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorMessage(FEIGN_INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleUnexpectedException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorMessage(INTERNAL_SERVER_ERROR));
    }
}
