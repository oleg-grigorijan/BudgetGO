package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.User;

import java.util.Optional;

public interface UsersRepository extends Repository<User, Long> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
