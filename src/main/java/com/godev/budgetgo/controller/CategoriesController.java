package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.service.request.CategoriesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesRequestService requestService;

    public CategoriesController(CategoriesRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody @Valid CategoryCreationDto creationDto) {
        Long newCategoryId = requestService.create(creationDto).getId();
        response.addHeader("Location", "/api/categories/" + newCategoryId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryInfoDto get(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public CategoryInfoDto patch(@PathVariable Long id, @RequestBody @Valid CategoryPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
