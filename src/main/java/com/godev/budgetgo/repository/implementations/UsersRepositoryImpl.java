package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.repository.UsersRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UsersRepositoryImpl
        extends AbstractRepository<User, Long>
        implements UsersRepository {

    UsersRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.login = :login", entityClass)
                .setParameter("login", login)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email", entityClass)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
