package com.godev.budgetgo.api.rest.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("User settings info model")
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
