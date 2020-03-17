package com.godev.budgetgo.api.rest.operation.dto;

import com.godev.budgetgo.domain.operation.Category;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("Category creation model")
@Data
@NoArgsConstructor
public class CategoryCreationDto {

    @NotBlank
    @Size(max = Category.NAME_MAX_LENGTH)
    private String name;
}
