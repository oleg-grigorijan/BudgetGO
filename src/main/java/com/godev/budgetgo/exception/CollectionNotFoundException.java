package com.godev.budgetgo.exception;

import com.godev.budgetgo.entity.UserCategoryKey;

public class CollectionNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Collection not found";

    public CollectionNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public CollectionNotFoundException(String message) {
        super(message);
    }

    public static CollectionNotFoundException byId(UserCategoryKey id) {
        return new CollectionNotFoundException("Collection not found for user with id = " + id.getUserId() +
                " for category with id = " + id);
    }
}
