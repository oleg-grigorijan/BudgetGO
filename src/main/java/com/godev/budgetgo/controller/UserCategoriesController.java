package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.UserCategoryCreationDto;
import com.godev.budgetgo.dto.UserCategoryInfoDto;
import com.godev.budgetgo.dto.UserCategoryPatchesDto;
import com.godev.budgetgo.request.UserCategoriesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/me/categories")
public class UserCategoriesController {
    private final UserCategoriesRequestService requestService;

    public UserCategoriesController(UserCategoriesRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserCategoryInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCategoryInfoDto create(HttpServletResponse response, @RequestBody @Valid UserCategoryCreationDto creationDto) {
        UserCategoryInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/me/categories/" + creationDto.getCategoryId());
        return createdDto;
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public UserCategoryInfoDto getByCategoryId(@PathVariable Long categoryId) {
        return requestService.getByCategoryId(categoryId);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public UserCategoryInfoDto patch(@PathVariable Long categoryId, @RequestBody @Valid UserCategoryPatchesDto patchesDto) {
        return requestService.patchByCategoryId(categoryId, patchesDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long categoryId) {
        requestService.deleteByCategoryId(categoryId);
    }
}
