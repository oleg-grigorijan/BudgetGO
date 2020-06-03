package com.godev.budgetgo.api.rest.operation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("Category info model")
@Data
public class CategoryInfoDto {

    private Long id;

    private String name;
}
