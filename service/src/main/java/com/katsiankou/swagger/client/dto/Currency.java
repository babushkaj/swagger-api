package com.katsiankou.swagger.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @JsonProperty("Cur_ID")
    private int id;

    @JsonProperty("Cur_Code")
    private String code;

    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty("Cur_Name_Bel")
    private String nameBy;

    @JsonProperty("Cur_Name_Eng")
    private String nameEn;

    @JsonProperty("Cur_Name")
    private String nameRu;
}
