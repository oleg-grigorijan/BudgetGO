package com.godev.budgetgo.authorization;

import com.godev.budgetgo.entity.UserStorageRole;

public interface UserStorageRoleAuthoritiesService {
    boolean canModifyStorage(UserStorageRole who);

    boolean canBeCreatedBy(UserStorageRole who, UserStorageRole by);

    boolean canBeModifiedBy(UserStorageRole who, UserStorageRole by);
}
