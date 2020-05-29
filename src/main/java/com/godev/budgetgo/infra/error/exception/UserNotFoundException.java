package com.godev.budgetgo.infra.error.exception;

public class UserNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "User not found";

    public UserNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException byId(Long id) {
        return new UserNotFoundException("User not found by id = " + id);
    }

    public static UserNotFoundException byLogin(String login) {
        return new UserNotFoundException("User not found by login = " + login);
    }

    public static UserNotFoundException byEmail(String email) {
        return new UserNotFoundException("User not found by email = " + email);
    }
}
