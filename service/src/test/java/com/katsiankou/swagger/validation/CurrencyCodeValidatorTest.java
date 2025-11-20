package com.katsiankou.swagger.validation;

import com.katsiankou.models.currency.Currency;
import com.katsiankou.swagger.service.CurrencyService;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyCodeValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder builder;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyCodeValidator validator;

    @Test
    void shouldReturnFalseIfCurrencyAbbreviationNull() {
        doReturn(builder).when(context).buildConstraintViolationWithTemplate(anyString());

        boolean result = validator.isValid(null, context);

        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("Invalid currency abbreviation: 'null'");
        verify(builder).addConstraintViolation();
    }

    @Test
    void shouldReturnFalseIfInvalidCurrencyAbbreviation() {
        doReturn(builder).when(context).buildConstraintViolationWithTemplate(anyString());

        doReturn(Map.of()).when(currencyService).getCurrencies();

        boolean result = validator.isValid("invalid", context);

        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("Invalid currency abbreviation: 'invalid'");
        verify(builder).addConstraintViolation();
    }

    @Test
    void shouldReturnTrueIfValidCurrencyAbbreviation() {
        doReturn(Map.of("USD", mock(Currency.class))).when(currencyService).getCurrencies();

        boolean result = validator.isValid("usd", context);

        assertTrue(result);
        verify(context, never()).disableDefaultConstraintViolation();
    }

}