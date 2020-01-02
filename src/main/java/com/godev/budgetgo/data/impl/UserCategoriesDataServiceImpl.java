package com.godev.budgetgo.data.impl;

import com.godev.budgetgo.data.UserCategoriesDataService;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;
import com.godev.budgetgo.exception.UserCategoryNotFoundException;
import com.godev.budgetgo.repository.UserCategoriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCategoriesDataServiceImpl extends AbstractDataService<UserCategory, UserCategoryKey> implements UserCategoriesDataService {

    private final UserCategoriesRepository repository;

    public UserCategoriesDataServiceImpl(UserCategoriesRepository repository) {
        super(repository, UserCategoryNotFoundException::byId);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserCategory> getByUser(User user) {
        return repository.getByUser(user);
    }
}
