package com.godev.budgetgo.business.currency;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.business.currency.impl.CurrenciesDataServiceImpl;
import com.godev.budgetgo.business.currency.impl.CurrenciesRepository;
import com.godev.budgetgo.domain.currency.Currency;
import com.godev.budgetgo.infra.error.exception.CurrencyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class CurrenciesDataServiceTest {

    private CurrenciesDataService dataService;

    @Mock
    CurrenciesRepository repository;

    @BeforeEach
    void setUp() {
        dataService = new CurrenciesDataServiceImpl(repository);
    }

    @Test
    void getById_general_correctReturnValue() {
        Currency entity = new Currency();
        entity.setId(1L);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getById(entity.getId())).isSameAs(entity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(1L)).isInstanceOf(CurrencyNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<Currency> entities = new ArrayList<>();
        entities.add(new Currency());
        when(repository.findAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void add_general_repositoryAddCall() {
        Currency entity = new Currency();

        dataService.add(entity);
        verify(repository).save(refEq(entity));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        Currency entity = new Currency();
        entity.setId(1L);

        dataService.update(entity);
        verify(repository).save(refEq(entity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        Currency entity = new Currency();
        entity.setId(1L);

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }
}
