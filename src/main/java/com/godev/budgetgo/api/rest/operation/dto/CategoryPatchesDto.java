package com.godev.budgetgo.api.rest.operation.dto;

import com.godev.budgetgo.domain.operation.Category;
import com.godev.budgetgo.infra.validation.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.Optional;

@ApiModel("Category patches model")
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CategoryPatchesDto {

    @NullOrNotBlank
    @Size(max = Category.NAME_MAX_LENGTH)
    private String name;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
