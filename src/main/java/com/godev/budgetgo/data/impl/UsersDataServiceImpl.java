package com.godev.budgetgo.data.impl;

import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.UserNotFoundException;
import com.godev.budgetgo.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersDataServiceImpl extends AbstractDataService<User, Long> implements UsersDataService {

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
