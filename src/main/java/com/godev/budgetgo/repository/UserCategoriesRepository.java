package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;

import java.util.List;

public interface UserCategoriesRepository extends Repository<UserCategory, UserCategoryKey> {
    List<UserCategory> getByUser(User user);
}
