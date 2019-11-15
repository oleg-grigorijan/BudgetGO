package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;

import java.util.List;

public interface CollectionsRepository extends Repository<Collection, UserCategoryKey> {
    List<Collection> getByUser(User user);
}
