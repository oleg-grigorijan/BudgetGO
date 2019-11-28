package com.godev.budgetgo.exception;

import com.godev.budgetgo.entity.UserCategoryKey;

public class UserCategoryNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "User category not found";

    public UserCategoryNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public UserCategoryNotFoundException(String message) {
        super(message);
    }

    public static UserCategoryNotFoundException byId(UserCategoryKey id) {
        return new UserCategoryNotFoundException("Category with id = " + id.getCategoryId()
                + "not found for user with id = " + id.getUserId());
    }
}
