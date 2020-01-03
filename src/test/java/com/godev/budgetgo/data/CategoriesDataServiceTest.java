package com.godev.budgetgo.data;

import com.godev.budgetgo.data.impl.CategoriesDataServiceImpl;
import com.godev.budgetgo.entity.Category;
import com.godev.budgetgo.exception.CategoryNotFoundException;
import com.godev.budgetgo.repository.CategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriesDataServiceTest {

    private CategoriesDataService dataService;

    @Mock
    CategoriesRepository repository;

    @BeforeEach
    void setUp() {
        dataService = new CategoriesDataServiceImpl(repository);
    }

    @Test
    void getById_general_correctReturnValue() {
        Category entity = new Category();
        entity.setId(1L);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getById(entity.getId())).isSameAs(entity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(1L)).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<Category> entities = new ArrayList<>();
        entities.add(new Category());
        when(repository.getAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void getByName_general_correctReturnValue() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("abc");

        when(repository.findByName(entity.getName())).thenReturn(Optional.of(entity));

        assertThat(dataService.getByName(entity.getName())).isSameAs(entity);
    }

    @Test
    void getByName_nonExistingEntity_exceptionThrown() {
        when(repository.findByName(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getByName("abc")).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    void add_general_repositoryAddCall() {
        Category entity = new Category();

        dataService.add(entity);
        verify(repository).add(refEq(entity));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        Category entity = new Category();
        entity.setId(1L);

        dataService.update(entity);
        verify(repository).update(refEq(entity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        Category entity = new Category();
        entity.setId(1L);

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }
}
