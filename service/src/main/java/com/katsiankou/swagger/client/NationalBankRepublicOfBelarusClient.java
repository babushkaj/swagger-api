package com.katsiankou.swagger.client;

import com.katsiankou.swagger.client.dto.Currency;
import com.katsiankou.swagger.client.dto.CurrencyRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "nationalBankRepublicOfBelarusClient", url = "${com.katsiankou.downstream.nbrb-api-url}")
public interface NationalBankRepublicOfBelarusClient {

    @GetMapping("/exrates/currencies")
    List<Currency> getAllCurrencies();

    @GetMapping("/exrates/rates/{currencyAbbreviation}?parammode=2")
    CurrencyRate getCurrencyRate(@PathVariable String currencyAbbreviation);

}
