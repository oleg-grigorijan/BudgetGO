package com.godev.budgetgo.validation.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NullOrNotBlankValidatorTest {

    private NullOrNotBlankValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new NullOrNotBlankValidator();
    }

    @Test
    void isValid_null_true() {
        assertThat(validator.isValid(null, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "  a", "b  ", "  c  "})
    void isValid_notBlank_true(String source) {
        assertThat(validator.isValid(source, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void isValid_blank_false(String source) {
        assertThat(validator.isValid(source, context)).isFalse();
    }

}
