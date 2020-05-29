package com.godev.budgetgo.api.rest.operation.dto;

import com.godev.budgetgo.domain.operation.Operation;
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
    private LocalDate date;

    @Size(max = Operation.DESCRIPTION_MAX_LENGTH)
    private String description = "";
}
