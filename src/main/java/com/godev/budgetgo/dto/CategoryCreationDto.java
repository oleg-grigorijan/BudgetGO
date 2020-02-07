package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CategoryCreationDto {

    @NotBlank
    @Size(max = Category.NAME_MAX_LENGTH)
    private String name;
}
