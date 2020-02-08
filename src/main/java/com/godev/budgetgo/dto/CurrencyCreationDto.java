package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Currency;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("Currency creation model")
@Data
@NoArgsConstructor
public class CurrencyCreationDto {

    @NotBlank
    @Size(max = Currency.NAME_MAX_LENGTH)
    private String name;

    @NotBlank
    @Size(min = Currency.ISO_CODE_LENGTH, max = Currency.ISO_CODE_LENGTH)
    private String isoCode;
}
