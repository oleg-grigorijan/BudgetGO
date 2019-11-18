package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrenciesController {

    private final CurrenciesRequestService requestService;

    public CurrenciesController(CurrenciesRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyInfoDto> getAll() {
        return requestService.getAll();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody @Valid CurrencyCreationDto creationDto) {
        Long newOperationId = requestService.create(creationDto).getId();
        response.addHeader("Location", "/api/currencies/" + newOperationId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyInfoDto get(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyInfoDto patch(@PathVariable Long id, @RequestBody @Valid CurrencyPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
