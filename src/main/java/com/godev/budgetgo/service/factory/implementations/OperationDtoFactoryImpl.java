package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.service.converter.CategoriesConverter;
import com.godev.budgetgo.service.converter.UsersConverter;
import com.godev.budgetgo.service.factory.OperationDtoFactory;
import org.springframework.stereotype.Service;

@Service
class OperationDtoFactoryImpl implements OperationDtoFactory {

    private final CategoriesConverter categoriesConverter;
    private final UsersConverter usersConverter;

    public OperationDtoFactoryImpl(CategoriesConverter categoriesConverter, UsersConverter usersConverter) {
        this.categoriesConverter = categoriesConverter;
        this.usersConverter = usersConverter;
    }

    @Override
    public OperationInfoDto createFrom(Operation e) {
        OperationInfoDto dto = new OperationInfoDto();
        dto.setId(e.getId().getOperationId());
        dto.setCategoryInfoDto(categoriesConverter.convertFromEntity(e.getCategory()));
        dto.setMoneyDelta(e.getMoneyDelta());
        dto.setDate(e.getDate());
        dto.setDescription(e.getDescription());
        dto.setDateCreated(e.getDateCreated());
        dto.setDateModified(e.getDateModified());
        dto.setLastEditorInfoDto(usersConverter.convertFromEntity(e.getLastEditor()));
        dto.setCreatorInfoDto(usersConverter.convertFromEntity(e.getCreator()));
        return dto;
    }
}
