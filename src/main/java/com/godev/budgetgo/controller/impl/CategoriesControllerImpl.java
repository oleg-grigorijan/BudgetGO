package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.CategoriesController;
import com.godev.budgetgo.dto.CategoryCreationDto;
import com.godev.budgetgo.dto.CategoryInfoDto;
import com.godev.budgetgo.dto.CategoryPatchesDto;
import com.godev.budgetgo.request.CategoriesRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoriesControllerImpl implements CategoriesController {

    private final CategoriesRequestService requestService;

    public CategoriesControllerImpl(CategoriesRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<CategoryInfoDto> getAll() {
        return requestService.getAll();
    }

    @Override
    public CategoryInfoDto getByName(HttpServletResponse response, String name) {
        return requestService.getByName(name);
    }

    @Override
    public CategoryInfoDto create(HttpServletResponse response, @Valid CategoryCreationDto creationDto) {
        CategoryInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/categories/" + createdDto.getId());
        return createdDto;
    }

    @Override
    public CategoryInfoDto getById(Long id) {
        return requestService.getById(id);
    }

    @Override
    public CategoryInfoDto patchById(Long id, @Valid CategoryPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
