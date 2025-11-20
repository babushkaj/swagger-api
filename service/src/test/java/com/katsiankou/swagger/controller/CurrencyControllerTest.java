package com.katsiankou.swagger.controller;

import com.katsiankou.models.currency.Currency;
import com.katsiankou.models.currency.CurrencyRate;
import com.katsiankou.swagger.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CurrencyControllerTest {

    private static final int CURRENCY_ID = 1;
    private static final String CURRENCY_CODE = "123";
    private static final String CURRENCY_ABBREVIATION = "USD";
    private static final String CURRENCY_NAME_BY = "Долар ЗША";
    private static final String CURRENCY_NAME_EN = "US Dollar";
    private static final String CURRENCY_NAME_RU = "Доллар США";
    private static final String CURRENCY_RATE_DATE = "2025-10-09T00:00:00";
    private static final int CURRENCY_RATE_SCALE = 1;
    private static final BigDecimal CURRENCY_RATE = new BigDecimal("3.0122");


    @Mock
    private CurrencyService service;

    @InjectMocks
    private CurrencyController controller;

    @Test
    void shouldReturnCurrencies() {
        doReturn(Map.of(CURRENCY_ABBREVIATION, getCurrency())).when(service).getCurrencies();

        ResponseEntity<Collection<Currency>> result = controller.getCurrencies();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void shouldReturnCurrencyRate() {
        CurrencyRate currencyRate = getCurrencyRate();
        doReturn(currencyRate).when(service).getCurrencyRate(CURRENCY_ABBREVIATION);

        ResponseEntity<CurrencyRate> result = controller.getCurrencyRate(CURRENCY_ABBREVIATION);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(currencyRate, result.getBody());
    }

    private Currency getCurrency() {
        return new Currency(CURRENCY_ID, CURRENCY_CODE, CURRENCY_ABBREVIATION, CURRENCY_NAME_BY, CURRENCY_NAME_EN, CURRENCY_NAME_RU);
    }

    private CurrencyRate getCurrencyRate() {
        return new CurrencyRate(CURRENCY_ID, CURRENCY_RATE_DATE, CURRENCY_ABBREVIATION, CURRENCY_RATE_SCALE, CURRENCY_NAME_BY, CURRENCY_RATE);
    }
}