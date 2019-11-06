package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;

import java.util.List;

public interface StoragesRepository extends Repository<Storage, Long> {
    List<Storage> findByUser(User user);
}
