package com.godev.budgetgo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.json.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiModel("Operation creation model")
@Data
@NoArgsConstructor
public class OperationCreationDto {

    private long moneyDelta;

    @NotNull
    private Long categoryId;

    @NotNull
    @PastOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @Size(max = Operation.DESCRIPTION_MAX_LENGTH)
    private String description = "";
}
