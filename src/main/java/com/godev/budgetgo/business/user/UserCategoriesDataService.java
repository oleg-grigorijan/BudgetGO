package com.godev.budgetgo.business.user;

import com.godev.budgetgo.business.base.DataService;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.domain.user.UserCategory;
import com.godev.budgetgo.domain.user.UserCategoryKey;

import java.util.List;

public interface UserCategoriesDataService extends DataService<UserCategory, UserCategoryKey> {

    List<UserCategory> getByUser(User user);
}
