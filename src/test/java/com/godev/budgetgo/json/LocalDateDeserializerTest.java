package com.godev.budgetgo.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.godev.budgetgo.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class LocalDateDeserializerTest {

    private LocalDateDeserializer deserializer;

    @Mock
    private JsonParser parser;

    @Mock
    private DeserializationContext context;

    @BeforeEach
    void setUp() {
        deserializer = new LocalDateDeserializer();
    }

    @Test
    void deserialize_general_correctReturnValue() throws IOException {
        when(parser.readValueAs(String.class)).thenReturn("1970-01-01");

        assertThat(deserializer.deserialize(parser, context)).isEqualTo(LocalDate.of(1970, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01-01-1970", "1970/01/01", "01.01.1970", "abc", ""})
    void deserialize_invalidDate_exceptionThrown(String source) throws IOException {
        when(parser.readValueAs(String.class)).thenReturn(source);

        assertThatThrownBy(() -> deserializer.deserialize(parser, context)).isInstanceOf(RuntimeException.class);
    }
}
