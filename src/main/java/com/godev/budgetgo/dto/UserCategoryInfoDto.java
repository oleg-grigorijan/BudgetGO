package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("User category info model")
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
