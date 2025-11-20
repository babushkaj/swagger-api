package com.katsiankou.swagger.controller;


import com.katsiankou.models.currency.Currency;
import com.katsiankou.models.currency.CurrencyRate;
import com.katsiankou.models.error.ErrorMessage;
import com.katsiankou.swagger.service.CurrencyService;
import com.katsiankou.swagger.validation.CurrencyCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Validated
@RestController
@RequestMapping(path = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @Operation(summary = "Show available currencies",
        description = "Returns a list of available currencies",
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                        schema = @Schema(
                            implementation = Collection.class
                        )
                    )
                ),
                description = "Returns a list of available currencies."
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                        implementation = ErrorMessage.class
                    )
                ),
                description = "Internal server error."
            )
        })
    @GetMapping
    public ResponseEntity<Collection<Currency>> getCurrencies() {
        return ResponseEntity.ok(currencyService.getCurrencies().values());
    }

    @Operation(summary = "Show a currency rate",
        description = "Returns a currency rate in BYN by the currency abbreviation",
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                        implementation = CurrencyRate.class
                    )
                ),
                description = "Returns a currency rate."
            ),
            @ApiResponse(
                responseCode = "400",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                        implementation = ErrorMessage.class
                    )
                ),
                description = "Invalid currency abbreviation."
            ),
            @ApiResponse(
                responseCode = "500",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                        implementation = ErrorMessage.class
                    )
                ),
                description = "Internal server error."
            )
        })
    @GetMapping("/{currencyAbbreviation}")
    public ResponseEntity<CurrencyRate> getCurrencyRate(@PathVariable @CurrencyCode String currencyAbbreviation) {
        return ResponseEntity.ok(currencyService.getCurrencyRate(currencyAbbreviation));
    }
}
