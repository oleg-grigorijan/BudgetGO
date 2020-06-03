package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.business.base.BaseDataService;
import com.godev.budgetgo.business.user.UserCategoriesDataService;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;
import com.godev.budgetgo.infra.error.exception.UserCategoryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCategoriesDataServiceImpl extends BaseDataService<UserCategory, UserCategoryKey> implements UserCategoriesDataService {

    private final UserCategoriesRepository repository;

    public UserCategoriesDataServiceImpl(UserCategoriesRepository repository) {
        super(repository, UserCategoryNotFoundException::byId);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserCategory> getByUser(User user) {
        return repository.findByUser(user);
    }
}
