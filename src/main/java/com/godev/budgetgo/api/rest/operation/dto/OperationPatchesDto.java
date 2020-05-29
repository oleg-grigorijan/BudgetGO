package com.godev.budgetgo.api.rest.operation.dto;

import com.godev.budgetgo.domain.operation.Operation;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;

@ApiModel("Operation patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class OperationPatchesDto {

    private Long moneyDelta;

    private Long categoryId;

    @PastOrPresent
    private LocalDate date;

    @Size(max = Operation.DESCRIPTION_MAX_LENGTH)
    private String description;

    public Optional<Long> getMoneyDelta() {
        return Optional.ofNullable(moneyDelta);
    }

    public Optional<Long> getCategoryId() {
        return Optional.ofNullable(categoryId);
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable(date);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
