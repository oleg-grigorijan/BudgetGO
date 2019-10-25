package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.exception.UserStorageRelationsNotFoundException;
import com.godev.budgetgo.repository.UsersStoragesRelationsRepository;
import com.godev.budgetgo.service.data.UsersStoragesRelationsDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UsersStoragesRelationsDataServiceImpl
        extends AbstractDataService<UserStorageRelations, UserStorageKey>
        implements UsersStoragesRelationsDataService {

    private final UsersStoragesRelationsRepository repository;

    public UsersStoragesRelationsDataServiceImpl(
            UsersStoragesRelationsRepository repository) {
        super(repository, UserStorageRelationsNotFoundException::new);
        this.repository = repository;
    }

    @Override
    public List<UserStorageRelations> getByStorageId(Long storageId) {
        return repository.findByStorageId(storageId);
    }
}
