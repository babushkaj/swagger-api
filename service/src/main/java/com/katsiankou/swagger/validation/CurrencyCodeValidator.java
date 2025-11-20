package com.katsiankou.swagger.validation;

import com.katsiankou.swagger.service.CurrencyService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public record CurrencyCodeValidator(CurrencyService currencyService) implements ConstraintValidator<CurrencyCode, String> {

    @Override
    public boolean isValid(String currencyAbbreviation, ConstraintValidatorContext constraintValidatorContext) {
        if (currencyAbbreviation != null && currencyService.getCurrencies().get(currencyAbbreviation.toUpperCase()) != null) {
            return true;
        } else {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Invalid currency abbreviation: '" + currencyAbbreviation + "'")
                .addConstraintViolation();
            return false;
        }
    }
}
