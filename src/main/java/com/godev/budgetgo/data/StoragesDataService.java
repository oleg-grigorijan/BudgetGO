package com.godev.budgetgo.data;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;

import java.util.List;

public interface StoragesDataService extends DataService<Storage, Long> {
    List<Storage> getByUser(User user);
}
