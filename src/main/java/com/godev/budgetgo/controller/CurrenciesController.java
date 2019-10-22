package com.godev.budgetgo.controller;

import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.service.CurrenciesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrenciesController {

    private final CurrenciesService currenciesService;

    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyInfoDto> getAll() {
        return currenciesService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyInfoDto get(@PathVariable Long id) {
        return currenciesService.findById(id);
    }
}
