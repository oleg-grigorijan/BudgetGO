package com.godev.budgetgo.service.request;

import com.godev.budgetgo.dto.CollectionCreationDto;
import com.godev.budgetgo.dto.CollectionInfoDto;

import java.util.List;

public interface CollectionsRequestService {
    List<CollectionInfoDto> getAll();

    CollectionInfoDto getByCategoryId(Long categoryId);

    CollectionInfoDto create(CollectionCreationDto creationDto);

    void deleteByCategoryId(Long categoryId);
}
