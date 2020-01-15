package com.godev.budgetgo.data;

import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategory;
import com.godev.budgetgo.entity.UserCategoryKey;

import java.util.List;

public interface UserCategoriesDataService extends DataService<UserCategory, UserCategoryKey> {
    List<UserCategory> getByUser(User user);
}
