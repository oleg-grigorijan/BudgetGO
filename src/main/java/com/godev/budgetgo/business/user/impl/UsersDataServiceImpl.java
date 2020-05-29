package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.business.base.BaseDataService;
import com.godev.budgetgo.business.user.UsersDataService;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.error.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersDataServiceImpl extends BaseDataService<User, Long> implements UsersDataService {

    private final UsersRepository repository;

    public UsersDataServiceImpl(UsersRepository repository) {
        super(repository, UserNotFoundException::byId);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) {
        return repository
                .findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.byEmail(email));
    }

    @Transactional(readOnly = true)
    @Override
    public User getByLogin(String login) {
        return repository
                .findByLogin(login)
                .orElseThrow(() -> UserNotFoundException.byLogin(login));
    }
}
