package com.godev.budgetgo.api.rest.operation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@ApiModel("Operation info model")
@Data
@NoArgsConstructor
public class OperationInfoDto {

    private Long id;

    private long moneyDelta;

    @JsonProperty("category")
    private CategoryInfoDto categoryInfoDto;

    private LocalDate date;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dateModified;

    @JsonProperty("lastEditor")
    private UserInfoDto lastEditorInfoDto;

    @JsonProperty("creator")
    private UserInfoDto creatorInfoDto;
}
