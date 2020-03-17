package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.operation.dto.CategoryCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryPatchesDto;
import com.godev.budgetgo.business.operation.impl.CategoriesRequestServiceImpl;
import com.godev.budgetgo.domain.operation.Category;
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
@UnitTest
class CategoriesRequestServiceTest {

    private CategoriesRequestService requestService;

    @Mock
    private CategoriesDataService dataService;

    @Mock
    private CategoriesConverter converter;

    @BeforeEach
    void setUp() {
        requestService = new CategoriesRequestServiceImpl(dataService, converter);
    }

    @Test
    void create_general_dataServiceAddCall() {
        Category category = new Category();
        when(converter.convertToEntity(any(CategoryCreationDto.class))).thenReturn(category);

        requestService.create(new CategoryCreationDto());
        verify(dataService).add(refEq(category));
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        when(dataService.getById(any(Long.class))).thenReturn(new Category());
        Category patchedCategory = new Category();
        when(converter.merge(any(Category.class), any(CategoryPatchesDto.class))).thenReturn(patchedCategory);

        requestService.patch(1L, new CategoryPatchesDto());
        verify(dataService).update(refEq(patchedCategory));
    }
}
