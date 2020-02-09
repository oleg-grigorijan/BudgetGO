package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoriesRepository extends JpaRepository<UserCategory, UserCategoryKey> {

    List<UserCategory> findByUser(User user);
}
