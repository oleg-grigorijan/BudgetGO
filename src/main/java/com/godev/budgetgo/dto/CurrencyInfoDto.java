package com.godev.budgetgo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Currency info model")
@Data
@NoArgsConstructor
public class CurrencyInfoDto {

    private Long id;

    private String name;

    private String isoCode;
}
