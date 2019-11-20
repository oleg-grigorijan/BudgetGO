package com.godev.budgetgo.data;

import com.godev.budgetgo.entity.Collection;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserCategoryKey;

import java.util.List;

public interface CollectionsDataService extends DataService<Collection, UserCategoryKey> {
    List<Collection> getByUser(User user);
}
