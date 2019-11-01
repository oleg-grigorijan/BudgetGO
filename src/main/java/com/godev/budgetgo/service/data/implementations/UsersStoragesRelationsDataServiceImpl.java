package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.exception.UserStorageRelationsNotFoundException;
import com.godev.budgetgo.repository.UsersStoragesRelationsRepository;
import com.godev.budgetgo.service.data.StoragesDataService;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class UsersStoragesRelationsDataServiceImpl
        extends AbstractDataService<UserStorageRelations, UserStorageKey>
        implements UsersStoragesRelationsDataService {

    private final UsersStoragesRelationsRepository repository;
    private final StoragesDataService storagesDataService;

    public UsersStoragesRelationsDataServiceImpl(
            UsersStoragesRelationsRepository repository,
            StoragesDataService storagesDataService) {
        super(repository, UserStorageRelationsNotFoundException::new);
        this.repository = repository;
        this.storagesDataService = storagesDataService;
    }

    @Override
    public List<UserStorageRelations> getByStorage(Storage storage) {
        return repository.findByStorage(storage);
    }

    @Transactional
    @Override
    public void delete(UserStorageRelations entity) {
        super.delete(entity);
        Storage storage = entity.getStorage();
        if (getByStorage(storage).isEmpty()) {
            storagesDataService.delete(storage);
        }
    }
}
