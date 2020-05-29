package com.godev.budgetgo.api.rest.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
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
