package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.business.base.DataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.user.User;

import java.util.List;

public interface StoragesDataService extends DataService<Storage, Long> {

    List<Storage> getByUser(User user);
}
