package com.katsiankou.swagger.service;

import com.katsiankou.models.currency.Currency;
import com.katsiankou.models.currency.CurrencyRate;
import com.katsiankou.swagger.client.NationalBankRepublicOfBelarusClient;
import com.katsiankou.swagger.mapper.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final NationalBankRepublicOfBelarusClient client;
    private final CurrencyMapper currencyMapper;

    @Cacheable(cacheNames = "currency-rates", key = "#currencyAbbreviation")
    public CurrencyRate getCurrencyRate(String currencyAbbreviation) {
        log.info("==> Cache MISS, loading the CurrencyRate for: {}", currencyAbbreviation);
        return currencyMapper.map(client.getCurrencyRate(currencyAbbreviation));
    }

    @Cacheable(cacheNames = "currencies")
    public Map<String, Currency> getCurrencies() {
        log.info("==> Cache MISS, loading Currencies list");
        return client.getAllCurrencies().stream()
            .map(currencyMapper::map)
            .collect(Collectors.toMap(Currency::abbreviation, Function.identity(),
                (existing, duplicate) -> {
                    log.warn("Duplicate abbreviation found. Existing entity {}. Duplicate entity: {}", existing, duplicate);
                    return existing;
                }));
    }
}
