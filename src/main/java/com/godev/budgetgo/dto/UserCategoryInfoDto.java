package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCategoryInfoDto {

    @JsonProperty("category")
    private CategoryInfoDto categoryInfoDto;

    @JsonProperty("isUsedForIncomes")
    private boolean usedForIncomes;

    @JsonProperty("isUsedForOutcomes")
    private boolean usedForOutcomes;
}
