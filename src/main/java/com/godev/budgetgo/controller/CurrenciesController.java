package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.service.request.CurrenciesRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyInfoDto get(@PathVariable Long id) {
        return currenciesService.getById(id);
    }
}
