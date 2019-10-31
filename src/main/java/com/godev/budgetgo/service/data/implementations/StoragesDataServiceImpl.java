package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.*;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class StoragesDataServiceImpl
        extends AbstractDataService<Storage, Long>
        implements StoragesDataService {

    private final OperationsDataService operationsDataService;
    private UsersStoragesRelationsDataService relationsDataService;

    public StoragesDataServiceImpl(
            StoragesRepository repository,
            OperationsDataService operationsDataService) {
        super(repository, StorageNotFoundException::new);
        this.operationsDataService = operationsDataService;
    }

    @Autowired
    public void setRelationsDataService(UsersStoragesRelationsDataService relationsDataService) {
        this.relationsDataService = relationsDataService;
    }

    @Transactional
    @Override
    public Storage addWithCreator(Storage entity, User creator) {
        Storage storage = super.add(entity);

        UserStorageRelations relations = new UserStorageRelations();
        relations.setId(new UserStorageKey(creator.getId(), storage.getId()));
        relations.setUser(creator);
        relations.setStorage(storage);
        relations.setUserRole(UserStorageRole.CREATOR);
        relations.setIncludedInUserStatistics(true);
        relationsDataService.add(relations);

        return storage;
    }

    @Transactional
    @Override
    public void delete(Storage entity) {
        operationsDataService.deleteByStorage(entity);
        super.delete(entity);
    }
}
