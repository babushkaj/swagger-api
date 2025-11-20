package com.katsiankou.swagger.mapper;

import com.katsiankou.swagger.client.dto.Currency;
import com.katsiankou.swagger.client.dto.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyMapper {

    com.katsiankou.models.currency.Currency map(Currency currency);

    com.katsiankou.models.currency.CurrencyRate map(CurrencyRate currencyRate);
}
