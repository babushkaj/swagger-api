package com.katsiankou.models.currency;

import java.math.BigDecimal;

public record CurrencyRate(
    int id,
    String date,
    String abbreviation,
    int scale,
    String name,
    BigDecimal rate) {
}
