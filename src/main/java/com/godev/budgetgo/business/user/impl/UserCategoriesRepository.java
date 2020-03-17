package com.godev.budgetgo.business.user.impl;

import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoriesRepository extends JpaRepository<UserCategory, UserCategoryKey> {

    List<UserCategory> findByUser(User user);
}
