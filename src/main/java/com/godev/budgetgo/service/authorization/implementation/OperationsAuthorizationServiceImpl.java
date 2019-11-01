package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.authorization.OperationsAuthorizationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class OperationsAuthorizationServiceImpl implements OperationsAuthorizationService {
    @Override
    public List<Operation> getAuthorizedEntitiesByStorage(Storage storage) {
        return null;
    }

    @Override
    public List<Operation> getAuthorizedEntitiesByDateBetween(LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public List<Operation> getAuthorizedEntitiesByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public void authorizeDelete(Operation entity) {

    }

    @Override
    public List<Operation> getAllAuthorizedEntities() {
        return null;
    }

    @Override
    public void authorizeGet(Operation entity) {

    }

    @Override
    public void authorizeCreate(OperationCreationDto creationDto) {

    }

    @Override
    public void authorizePatch(Operation entity, OperationPatchesDto patchesDto) {

    }
}
