package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@ApiModel("User category patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserCategoryPatchesDto {

    @JsonProperty("isUsedForIncomes")
    private Boolean usedForIncomes;

    @JsonProperty("isUsedForOutcomes")
    private Boolean usedForOutcomes;

    public Optional<Boolean> getUsedForIncomes() {
        return Optional.ofNullable(usedForIncomes);
    }

    public Optional<Boolean> getUsedForOutcomes() {
        return Optional.ofNullable(usedForOutcomes);
    }
}
