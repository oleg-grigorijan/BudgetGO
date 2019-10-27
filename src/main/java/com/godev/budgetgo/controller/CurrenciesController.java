package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrenciesController {

    private final CurrenciesRequestService currenciesService;

    public CurrenciesController(CurrenciesRequestService currenciesService) {
        this.currenciesService = currenciesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyInfoDto> getAll() {
        return currenciesService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(HttpServletResponse response, @RequestBody CurrencyCreationDto creationDto) {
        Long newOperationId = currenciesService.create(creationDto).getId();
        response.addHeader("Location", "/api/currencies/" + newOperationId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyInfoDto get(@PathVariable Long id) {
        return currenciesService.getById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyInfoDto patch(@PathVariable Long id, @RequestBody CurrencyPatchesDto patches) {
        return currenciesService.patch(id, patches);
    }
}
