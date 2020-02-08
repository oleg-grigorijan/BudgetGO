package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel("User category creation model")
@Data
@NoArgsConstructor
public class UserCategoryCreationDto {

    @NotNull
    private Long categoryId;

    @JsonProperty("isUsedForIncomes")
    private boolean usedForIncomes = true;

    @JsonProperty("isUsedForOutcomes")
    private boolean usedForOutcomes = true;
}
