package com.katsiankou.swagger.controller.handler;

import com.katsiankou.models.error.ErrorMessage;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class CurrencyControllerExceptionHandlerTest {

    private final CurrencyControllerExceptionHandler handler = new CurrencyControllerExceptionHandler();

    @Test
    void shouldHandleConstraintViolationException() {
        String errorMessage = "error message";
        var violation = mock(ConstraintViolation.class);
        doReturn(errorMessage).when(violation).getMessage();
        var exception = mock(ConstraintViolationException.class);
        doReturn(Set.of(violation)).when(exception).getConstraintViolations();

        ResponseEntity<ErrorMessage> result = handler.handleConstraintViolationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(errorMessage, result.getBody().error());
    }

    @Test
    void shouldHandleFeignException() {
        var exception = mock(FeignException.class);

        ResponseEntity<ErrorMessage> result = handler.handleFeignException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("A problem occurred while calling NBRB API. Please, try again.", result.getBody().error());
    }

    @Test
    void shouldHandleUnexpectedException() {
        var exception = mock(Exception.class);

        ResponseEntity<ErrorMessage> result = handler.handleUnexpectedException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Internal server error. Please, try again.", result.getBody().error());
    }
}