package com.godev.budgetgo.authorization.impl;

import com.godev.budgetgo.authorization.UserStorageRoleAuthoritiesService;
import com.godev.budgetgo.entity.UserStorageRole;
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
