package com.godev.budgetgo.infra.authorization;

import com.godev.budgetgo.domain.storage.Storage;

import java.util.List;

public interface StoragesAuthorizationService {

    List<Storage> getAllAuthorizedEntities();

    void authorizeAccess(Storage entity);

    void authorizeModificationAccess(Storage entity);
}
