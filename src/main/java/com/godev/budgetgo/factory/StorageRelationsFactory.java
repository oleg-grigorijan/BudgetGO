package com.godev.budgetgo.factory;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageRole;

public interface StorageRelationsFactory {

    /**
     * Creates an instance of {@link StorageRelations} with the given storage, authenticated user, {@link UserStorageRole#CREATOR} role and {@code
     * isInvitation = false}.
     *
     * @param storage relations holder
     * @return created instance
     */
    StorageRelations createCreatorEntityForStorage(Storage storage);
}
