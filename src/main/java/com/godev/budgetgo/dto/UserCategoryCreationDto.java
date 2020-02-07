package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
