package com.godev.budgetgo.api.rest.user.impl;

import com.godev.budgetgo.api.rest.user.UserCategoriesController;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.business.user.UserCategoriesRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserCategoriesControllerImpl implements UserCategoriesController {

    private final UserCategoriesRequestService requestService;

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
