package com.katsiankou.models.currency;

public record Currency(
    int id,
    String code,
    String abbreviation,
    String nameBy,
    String nameEn,
    String nameRu) {
}
