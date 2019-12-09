package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class UserCategoryPatchesDto {

    @JsonProperty("isUsedForIncomes")
    private Boolean usedForIncomes;

    @JsonProperty("isUsedForOutcomes")
    private Boolean usedForOutcomes;

    public Optional<Boolean> getUsedForIncomes() {
        return Optional.ofNullable(usedForIncomes);
    }

    public void setUsedForIncomes(Boolean usedForIncomes) {
        this.usedForIncomes = usedForIncomes;
    }

    public Optional<Boolean> getUsedForOutcomes() {
        return Optional.ofNullable(usedForOutcomes);
    }

    public void setUsedForOutcomes(Boolean usedForOutcomes) {
        this.usedForOutcomes = usedForOutcomes;
    }
}
