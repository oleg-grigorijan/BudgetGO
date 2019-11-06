package com.godev.budgetgo.service.authorization.implementation;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.service.authorization.OperationsAuthorizationService;
import com.godev.budgetgo.service.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.service.data.OperationsDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class OperationsAuthorizationServiceImpl implements OperationsAuthorizationService {
    private final StoragesAuthorizationService storagesAuthorizationService;
    private final OperationsDataService dataService;

    public OperationsAuthorizationServiceImpl(
            StoragesAuthorizationService storagesAuthorizationService,
            OperationsDataService dataService) {
        this.storagesAuthorizationService = storagesAuthorizationService;
        this.dataService = dataService;
    }

    @Override
    public List<Operation> getAuthorizedEntitiesByStorage(Storage storage) {
        storagesAuthorizationService.authorizeGet(storage);
        return dataService.getByStorage(storage);
    }

    @Override
    public List<Operation> getAuthorizedEntitiesByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to) {
        storagesAuthorizationService.authorizeGet(storage);
        return dataService.getByStorageAndDateBetween(storage, from, to);
    }

    @Override
    public void authorizeDelete(Operation entity) {
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
    }

    @Override
    public List<Operation> getAllAuthorizedEntities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void authorizeGet(Operation entity) {
        storagesAuthorizationService.authorizeGet(entity.getStorage());
    }

    @Override
    public void authorizeCreate(Operation entity) {
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
    }

    @Override
    public void authorizePatch(Operation entity, Operation patchedEntity) {
        storagesAuthorizationService.authorizeModificationAccess(entity.getStorage());
    }
}
