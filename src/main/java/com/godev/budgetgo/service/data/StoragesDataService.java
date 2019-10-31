package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;

public interface StoragesDataService extends DataService<Storage, Long> {
    Storage addWithCreator(Storage entity, User creator);
}
