package com.godev.budgetgo.api.rest.currency.dto;

import com.godev.budgetgo.domain.currency.Currency;
import com.godev.budgetgo.infra.validation.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.Optional;

@ApiModel("Currency patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CurrencyPatchesDto {

    @NullOrNotBlank
    @Size(max = Currency.NAME_MAX_LENGTH)
    private String name;

    @NullOrNotBlank
    @Size(min = Currency.ISO_CODE_LENGTH, max = Currency.ISO_CODE_LENGTH)
    private String isoCode;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getIsoCode() {
        return Optional.ofNullable(isoCode);
    }
}
