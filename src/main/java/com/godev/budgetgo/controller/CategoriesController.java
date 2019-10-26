package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.service.request.CategoriesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesRequestService categoriesService;

    public CategoriesController(
            CategoriesRequestService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryInfoDto> getAll() {
        return categoriesService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            HttpServletResponse response,
            @RequestBody CategoryCreationDto creationDto
    ) {
        Long newCategoryId = categoriesService.create(creationDto).getId();
        response.addHeader("Location", "/api/categories" + newCategoryId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryInfoDto get(@PathVariable Long id) {
        return categoriesService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryInfoDto patch(
            @PathVariable Long id,
            @RequestBody CategoryPatchesDto patchesDto
    ) {
        return categoriesService.patch(id, patchesDto);
    }
}
