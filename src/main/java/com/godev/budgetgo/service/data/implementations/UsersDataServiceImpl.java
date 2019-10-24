package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.UserNotFoundException;
import com.godev.budgetgo.repository.UsersRepository;
import com.godev.budgetgo.service.data.UsersDataService;
import org.springframework.stereotype.Service;

@Service
class UsersDataServiceImpl
        extends AbstractDataService<User, Long>
        implements UsersDataService {

    private final UsersRepository repository;

    public UsersDataServiceImpl(UsersRepository repository) {
        super(repository, UserNotFoundException::new);
        this.repository = repository;
    }

    @Override
    public User getByEmail(String email) {
        return repository
                .findByEmail(email)
                .orElseThrow(notFoundExceptionSupplier);
    }

    @Override
    public User getByLogin(String login) {
        return repository
                .findByLogin(login)
                .orElseThrow(notFoundExceptionSupplier);
    }
}
