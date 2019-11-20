package com.godev.budgetgo.service.factory.implementations;

import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.service.converter.CategoriesConverter;
import com.godev.budgetgo.service.factory.OperationDtoFactory;
import com.godev.budgetgo.service.factory.UserDtoFactory;
import org.springframework.stereotype.Service;

@Service
class OperationDtoFactoryImpl implements OperationDtoFactory {
    private final CategoriesConverter categoriesConverter;
    private final UserDtoFactory userDtoFactory;

    public OperationDtoFactoryImpl(CategoriesConverter categoriesConverter, UserDtoFactory userDtoFactory) {
        this.categoriesConverter = categoriesConverter;
        this.userDtoFactory = userDtoFactory;
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
        dto.setLastEditorInfoDto(userDtoFactory.createFrom(e.getLastEditor()));
        dto.setCreatorInfoDto(userDtoFactory.createFrom(e.getCreator()));
        return dto;
    }
}
