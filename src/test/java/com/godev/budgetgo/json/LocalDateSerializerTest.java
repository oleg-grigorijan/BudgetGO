package com.godev.budgetgo.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocalDateSerializerTest {

    private LocalDateSerializer serializer;

    @Mock
    private JsonGenerator generator;

    @Mock
    private SerializerProvider provider;

    @BeforeEach
    void setUp() {
        serializer = new LocalDateSerializer();
    }

    @Test
    void serialize_general_generatorWriteStringCallWithCorrectParameter() throws IOException {
        serializer.serialize(LocalDate.of(1970, 1, 1), generator, provider);
        verify(generator).writeString("1970-01-01");
    }
}
