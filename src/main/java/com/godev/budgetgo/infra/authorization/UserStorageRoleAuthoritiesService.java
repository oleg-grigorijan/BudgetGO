package com.godev.budgetgo.infra.authorization;

import com.godev.budgetgo.domain.storage.UserStorageRole;

public interface UserStorageRoleAuthoritiesService {

    boolean canModifyStorage(UserStorageRole who);

    boolean canBeCreatedBy(UserStorageRole who, UserStorageRole by);

    boolean canBeModifiedBy(UserStorageRole who, UserStorageRole by);
}
