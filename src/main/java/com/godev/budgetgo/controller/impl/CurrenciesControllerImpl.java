package com.godev.budgetgo.controller.impl;

import com.godev.budgetgo.controller.CurrenciesController;
import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyInfoDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.request.CurrenciesRequestService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CurrenciesControllerImpl implements CurrenciesController {

    private final CurrenciesRequestService requestService;

    public CurrenciesControllerImpl(CurrenciesRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<CurrencyInfoDto> getAll() {
        return requestService.getAll();
    }

    @Override
    public CurrencyInfoDto create(HttpServletResponse response, @Valid CurrencyCreationDto creationDto) {
        CurrencyInfoDto createdDto = requestService.create(creationDto);
        response.addHeader("Location", "/api/currencies/" + createdDto.getId());
        return createdDto;
    }

    @Override
    public CurrencyInfoDto getById(Long id) {
        return requestService.getById(id);
    }

    @Override
    public CurrencyInfoDto patchById(Long id, @Valid CurrencyPatchesDto patchesDto) {
        return requestService.patch(id, patchesDto);
    }
}
