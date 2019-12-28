package com.godev.budgetgo.request;

import com.godev.budgetgo.converter.CurrenciesConverter;
import com.godev.budgetgo.data.CurrenciesDataService;
import com.godev.budgetgo.dto.CurrencyCreationDto;
import com.godev.budgetgo.dto.CurrencyPatchesDto;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.request.impl.CurrenciesRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrenciesRequestServiceTest {

    private CurrenciesRequestService requestService;

    @Mock
    private CurrenciesDataService dataService;

    @Mock
    private CurrenciesConverter converter;

    @BeforeEach
    void setUp() {
        requestService = new CurrenciesRequestServiceImpl(dataService, converter);
    }

    @Test
    void create_general_dataServiceAddCall() {
        Currency currency = new Currency();
        when(converter.convertFromDto(any(CurrencyCreationDto.class))).thenReturn(currency);

        requestService.create(new CurrencyCreationDto());
        verify(dataService).add(refEq(currency));
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        when(dataService.getById(any(Long.class))).thenReturn(new Currency());
        Currency patchedCurrency = new Currency();
        when(converter.merge(any(Currency.class), any(CurrencyPatchesDto.class))).thenReturn(patchedCurrency);

        requestService.patch(1L, new CurrencyPatchesDto());
        verify(dataService).update(refEq(patchedCurrency));
    }
}
