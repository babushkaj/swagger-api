package com.katsiankou.swagger.mapper;

import com.katsiankou.swagger.client.dto.Currency;
import com.katsiankou.swagger.client.dto.CurrencyRate;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CurrencyMapperTest {

    private static final int CURRENCY_ID = 1;
    private static final String CURRENCY_CODE = "123";
    private static final String CURRENCY_ABBREVIATION = "USD";
    private static final String CURRENCY_NAME_BY = "Долар ЗША";
    private static final String CURRENCY_NAME_EN = "US Dollar";
    private static final String CURRENCY_NAME_RU = "Доллар США";
    private static final String CURRENCY_RATE_DATE = "2025-10-09T00:00:00";
    private static final int CURRENCY_RATE_SCALE = 1;
    private static final BigDecimal CURRENCY_RATE = new BigDecimal("3.0122");

    private final CurrencyMapper mapper = Mappers.getMapper(CurrencyMapper.class);

    @Test
    void shouldMapNullCurrency() {
        Currency currency = null;
        assertNull(mapper.map(currency));
    }

    @Test
    void shouldMapCurrencyWithNullFields() {
        var result = mapper.map(new Currency());

        assertEquals(0, result.id());
        assertNull(result.code());
        assertNull(result.abbreviation());
        assertNull(result.nameBy());
        assertNull(result.nameEn());
        assertNull(result.nameRu());
    }

    @Test
    void shouldMapValidCurrency() {
        com.katsiankou.models.currency.Currency result = mapper.map(getCurrency());

        assertEquals(CURRENCY_ID, result.id());
        assertEquals(CURRENCY_CODE, result.code());
        assertEquals(CURRENCY_ABBREVIATION, result.abbreviation());
        assertEquals(CURRENCY_NAME_BY, result.nameBy());
        assertEquals(CURRENCY_NAME_EN, result.nameEn());
        assertEquals(CURRENCY_NAME_RU, result.nameRu());
    }

    @Test
    void ShouldMapNullCurrencyRate() {
        CurrencyRate currencyRate = null;
        assertNull(mapper.map(currencyRate));
    }

    @Test
    void shouldMapCurrencyRateWithNullFields() {
        var result = mapper.map(new CurrencyRate());

        assertEquals(0, result.id());
        assertNull(result.date());
        assertNull(result.abbreviation());
        assertEquals(0, result.scale());
        assertNull(result.name());
        assertNull(result.rate());
    }

    @Test
    void shouldMapValidCurrencyRate() {
        var result = mapper.map(getCurrencyRate());

        assertEquals(CURRENCY_ID, result.id());
        assertEquals(CURRENCY_RATE_DATE, result.date());
        assertEquals(CURRENCY_ABBREVIATION, result.abbreviation());
        assertEquals(CURRENCY_RATE_SCALE, result.scale());
        assertEquals(CURRENCY_NAME_BY, result.name());
        assertEquals(CURRENCY_RATE, result.rate());
    }

    private Currency getCurrency() {
        return new Currency(CURRENCY_ID, CURRENCY_CODE, CURRENCY_ABBREVIATION, CURRENCY_NAME_BY, CURRENCY_NAME_EN, CURRENCY_NAME_RU);
    }

    private CurrencyRate getCurrencyRate() {
        return new CurrencyRate(CURRENCY_ID, CURRENCY_RATE_DATE, CURRENCY_ABBREVIATION, CURRENCY_RATE_SCALE, CURRENCY_NAME_BY, CURRENCY_RATE);
    }
}