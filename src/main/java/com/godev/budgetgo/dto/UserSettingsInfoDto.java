package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSettingsInfoDto {

    private Long id;

    private String login;

    private String email;

    private String name;

    private String surname;

    @JsonProperty("isEmailPublic")
    private Boolean emailPublic;

    @JsonProperty("mainCurrency")
    private CurrencyInfoDto mainCurrencyInfoDto;
}
