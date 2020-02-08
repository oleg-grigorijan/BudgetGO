package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
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
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Currencies")
@RequestMapping("/api/currencies")
public interface CurrenciesController {

    @ApiOperation("Returns all currencies")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CurrencyInfoDto> getAll();

    @ApiOperation(value = "Creates currency", notes = "Allowed only for users with ROLE_ADMIN")
    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    CurrencyInfoDto create(HttpServletResponse response, @RequestBody CurrencyCreationDto creationDto);

    @ApiOperation("Finds currency by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CurrencyInfoDto getById(@PathVariable Long id);

    @ApiOperation(value = "Patches currency found by id", notes = "Allowed only for users with ROLE_ADMIN")
    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    CurrencyInfoDto patchById(@PathVariable Long id, @RequestBody CurrencyPatchesDto patchesDto);
}
