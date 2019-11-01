package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;

import java.util.List;

public interface StoragesDataService extends DataService<Storage, Long> {
    List<Storage> getByUser(User user);

    Storage addWithCreator(Storage entity, User creator);
}
