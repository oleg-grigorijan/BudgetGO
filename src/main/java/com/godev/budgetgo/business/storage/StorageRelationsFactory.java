package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageRole;

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
