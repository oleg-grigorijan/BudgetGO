package com.godev.budgetgo.api.rest.currency.impl;

import com.godev.budgetgo.api.rest.currency.CurrenciesController;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyCreationDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyInfoDto;
import com.godev.budgetgo.api.rest.currency.dto.CurrencyPatchesDto;
import com.godev.budgetgo.business.currency.CurrenciesRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrenciesControllerImpl implements CurrenciesController {

    private final CurrenciesRequestService requestService;

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
