package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.godev.budgetgo.json.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OperationInfoDto {

    private Long id;

    private long moneyDelta;

    @JsonProperty("category")
    private CategoryInfoDto categoryInfoDto;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private String description;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateCreated;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateModified;

    @JsonProperty("lastEditor")
    private UserInfoDto lastEditorInfoDto;

    @JsonProperty("creator")
    private UserInfoDto creatorInfoDto;
}
