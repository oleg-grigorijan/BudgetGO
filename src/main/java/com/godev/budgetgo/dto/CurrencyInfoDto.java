package com.godev.budgetgo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyInfoDto {

    private Long id;

    private String name;

    private String isoCode;
}
