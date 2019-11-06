package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.entity.Storage;

public interface StoragesAuthorizationService
        extends AuthorizationService<Storage> {

    void authorizeModificationAccess(Storage entity);
}
