package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;

public interface StorageRelationsFactory {

    /**
     * Creates an instance of {@link StorageRelations} with the given storage,
     * authenticated user and {@link com.godev.budgetgo.entity.UserStorageRole#CREATOR} role.
     * Other parameters are set by default.
     *
     * @param storage relations holder
     * @return created instance
     */
    StorageRelations createCreatorEntityForStorage(Storage storage);
}
