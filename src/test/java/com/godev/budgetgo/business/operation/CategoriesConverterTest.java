package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.operation.dto.CategoryCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.CategoryPatchesDto;
import com.godev.budgetgo.business.operation.impl.CategoriesConverterImpl;
import com.godev.budgetgo.domain.operation.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class CategoriesConverterTest {

    private CategoriesConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CategoriesConverterImpl();
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        CategoryCreationDto dto = new CategoryCreationDto();
        dto.setName("abc");
        assertThat(dto).hasNoNullFieldsOrProperties();

        Category expectedEntity = new Category();
        expectedEntity.setName(dto.getName());
        assertThat(expectedEntity).hasNoNullFieldsOrPropertiesExcept("id");

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("abc");
        assertThat(entity).hasNoNullFieldsOrProperties();

        CategoryInfoDto expectedDto = new CategoryInfoDto();
        expectedDto.setId(entity.getId());
        expectedDto.setName(entity.getName());
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("abc");
        assertThat(entity).hasNoNullFieldsOrProperties();

        CategoryPatchesDto dto = new CategoryPatchesDto();
        dto.setName("cba");

        Category expectedEntity = new Category();
        expectedEntity.setId(entity.getId());
        expectedEntity.setName(dto.getName().get());
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_noEntityChanges() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("abc");
        assertThat(entity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new CategoryPatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
