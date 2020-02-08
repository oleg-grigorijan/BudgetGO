package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Categories")
@RequestMapping("/api/categories")
public interface CategoriesController {

    @ApiOperation("Returns all categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CategoryInfoDto> getAll();

    @ApiOperation("Finds category by name")
    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    CategoryInfoDto getByName(HttpServletResponse response, @RequestParam String name);

    @ApiOperation("Creates category")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryInfoDto create(HttpServletResponse response, @RequestBody CategoryCreationDto creationDto);

    @ApiOperation("Finds category by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CategoryInfoDto getById(@PathVariable Long id);

    @ApiOperation(value = "Patches category found by id", notes = "Allowed only for users with ROLE_ADMIN")
    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    CategoryInfoDto patchById(@PathVariable Long id, @RequestBody CategoryPatchesDto patchesDto);
}
