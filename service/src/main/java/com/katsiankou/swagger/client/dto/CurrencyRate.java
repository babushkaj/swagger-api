package com.katsiankou.swagger.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {

    @JsonProperty("Cur_ID")
    private int id;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty("Cur_Scale")
    private int scale;

    @JsonProperty("Cur_Name")
    private String name;

    @JsonProperty("Cur_OfficialRate")
    private BigDecimal rate;
}
