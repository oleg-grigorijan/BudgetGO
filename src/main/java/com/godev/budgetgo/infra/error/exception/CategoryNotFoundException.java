package com.godev.budgetgo.infra.error.exception;

public class CategoryNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Category not found";

    public CategoryNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException byId(Long id) {
        return new CategoryNotFoundException("Category not found by id = " + id);
    }

    public static CategoryNotFoundException byName(String name) {
        return new CategoryNotFoundException("Category not found by name = " + name);
    }
}
