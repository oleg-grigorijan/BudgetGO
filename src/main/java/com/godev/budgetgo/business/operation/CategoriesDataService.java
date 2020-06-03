package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.business.base.DataService;
import com.godev.budgetgo.domain.operation.Category;

public interface CategoriesDataService extends DataService<Category, Long> {

    Category getByName(String name);
}
