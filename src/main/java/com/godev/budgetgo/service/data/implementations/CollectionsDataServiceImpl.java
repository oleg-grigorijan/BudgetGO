package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.exception.CollectionNotFoundException;
import com.godev.budgetgo.repository.CollectionsRepository;
import com.godev.budgetgo.service.data.CollectionsDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class CollectionsDataServiceImpl
        extends AbstractDataService<Collection, UserCategoryKey>
        implements CollectionsDataService {

    private final CollectionsRepository repository;

    public CollectionsDataServiceImpl(CollectionsRepository repository) {
        super(repository, CollectionNotFoundException::byId);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Collection> getByUser(User user) {
        return repository.getByUser(user);
    }
}
