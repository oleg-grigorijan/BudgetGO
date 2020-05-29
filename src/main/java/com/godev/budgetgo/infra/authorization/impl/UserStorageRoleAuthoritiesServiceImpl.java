package com.godev.budgetgo.infra.authorization.impl;

import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.infra.authorization.UserStorageRoleAuthoritiesService;
import org.springframework.stereotype.Service;

@Service
public class UserStorageRoleAuthoritiesServiceImpl implements UserStorageRoleAuthoritiesService {

    @Override
    public boolean canModifyStorage(UserStorageRole who) {
        return who != UserStorageRole.VIEWER;
    }

    @Override
    public boolean canBeCreatedBy(UserStorageRole who, UserStorageRole by) {
        return who != UserStorageRole.CREATOR && (by == UserStorageRole.CREATOR || by == UserStorageRole.ADMIN);
    }

    @Override
    public boolean canBeModifiedBy(UserStorageRole who, UserStorageRole by) {
        return who != UserStorageRole.CREATOR && (by == UserStorageRole.CREATOR || by == UserStorageRole.ADMIN);
    }
}
