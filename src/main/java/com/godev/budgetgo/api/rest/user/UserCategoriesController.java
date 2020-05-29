package com.godev.budgetgo.api.rest.user;

import com.godev.budgetgo.api.rest.user.dto.UserCategoryCreationDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryInfoDto;
import com.godev.budgetgo.api.rest.user.dto.UserCategoryPatchesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "User categories")
@RequestMapping("/api/me/categories")
public interface UserCategoriesController {

    @ApiOperation("Returns all user categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserCategoryInfoDto> getAll();

    @ApiOperation("Adds category to user categories")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserCategoryInfoDto create(HttpServletResponse response, @RequestBody UserCategoryCreationDto creationDto);

    @ApiOperation("Finds user category by category id")
    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    UserCategoryInfoDto getByCategoryId(@PathVariable Long categoryId);

    @ApiOperation("Patches user category specified by category id")
    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    UserCategoryInfoDto patchByCategoryId(@PathVariable Long categoryId, @RequestBody UserCategoryPatchesDto patchesDto);

    @ApiOperation("Removes category specified by id from user categories")
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByCategoryId(@PathVariable Long categoryId);
}
