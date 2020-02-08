package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.request.UserCategoriesRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserCategoriesControllerImpl implements com.godev.budgetgo.controller.UserCategoriesController {

    private final UserCategoriesRequestService requestService;

    public UserCategoriesControllerImpl(UserCategoriesRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<UserCategoryInfoDto> getAll() {
        return requestService.getAll();
    }

    @Override
    public UserCategoryInfoDto create(HttpServletResponse response, @Valid UserCategoryCreationDto creationDto) {
        UserCategoryInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/me/categories/" + creationDto.getCategoryId());
        return createdDto;
    }

    @Override
    public UserCategoryInfoDto getByCategoryId(Long categoryId) {
        return requestService.getByCategoryId(categoryId);
    }

    @Override
    public UserCategoryInfoDto patchByCategoryId(Long categoryId, @Valid UserCategoryPatchesDto patchesDto) {
        return requestService.patchByCategoryId(categoryId, patchesDto);
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        requestService.deleteByCategoryId(categoryId);
    }
}
