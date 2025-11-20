package com.katsiankou.swagger.service;

import com.katsiankou.swagger.client.NationalBankRepublicOfBelarusClient;
import com.katsiankou.swagger.client.dto.Currency;
import com.katsiankou.swagger.client.dto.CurrencyRate;
import com.katsiankou.swagger.mapper.CurrencyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    public static final String CURRENCY_ABBREVIATION = "USD";

    @Mock
    private NationalBankRepublicOfBelarusClient client;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void shouldReturnCurrencies() {
        doReturn(List.of(mock(Currency.class))).when(client).getAllCurrencies();
        com.katsiankou.models.currency.Currency mappedCurrency = mock(com.katsiankou.models.currency.Currency.class);
        doReturn(CURRENCY_ABBREVIATION).when(mappedCurrency).abbreviation();
        doReturn(mappedCurrency).when(currencyMapper).map(any(Currency.class));

        Map<String, com.katsiankou.models.currency.Currency> result = currencyService.getCurrencies();

        assertEquals(1, result.size());
        verify(currencyMapper).map(any(Currency.class));
    }

    @Test
    void shouldReturnDedupedCurrencies() {
        doReturn(List.of(mock(Currency.class), mock(Currency.class))).when(client).getAllCurrencies();
        com.katsiankou.models.currency.Currency mappedCurrency = mock(com.katsiankou.models.currency.Currency.class);
        doReturn(CURRENCY_ABBREVIATION).when(mappedCurrency).abbreviation();
        doReturn(mappedCurrency).when(currencyMapper).map(any(Currency.class));

        Map<String, com.katsiankou.models.currency.Currency> result = currencyService.getCurrencies();

        assertEquals(1, result.size());
        verify(currencyMapper, times(2)).map(any(Currency.class));
    }

    @Test
    void shouldReturnCurrencyRate() {
        doReturn(mock(CurrencyRate.class)).when(client).getCurrencyRate(CURRENCY_ABBREVIATION);
        com.katsiankou.models.currency.CurrencyRate mappedCurrencyRate = mock(com.katsiankou.models.currency.CurrencyRate.class);
        doReturn(mappedCurrencyRate).when(currencyMapper).map(any(CurrencyRate.class));

        com.katsiankou.models.currency.CurrencyRate result = currencyService.getCurrencyRate(CURRENCY_ABBREVIATION);

        assertEquals(mappedCurrencyRate, result);
    }

}