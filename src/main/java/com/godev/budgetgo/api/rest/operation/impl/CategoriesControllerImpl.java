package com.godev.budgetgo.api.rest.operation.impl;

import com.godev.budgetgo.api.rest.operation.CategoriesController;
import com.godev.budgetgo.api.rest.operation.dto.CategoryCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryPatchesDto;
import com.godev.budgetgo.business.operation.CategoriesRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriesControllerImpl implements CategoriesController {

    private final CategoriesRequestService requestService;

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
