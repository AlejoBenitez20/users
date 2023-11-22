package com.global.users.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FieldsValidationsTest {

    @InjectMocks
    private FieldsValidations fieldsValidations;

    @Test
    void emailValidate() {

        // Given

        // When
        fieldsValidations.emailValidate("sdasd@hotmai.com");

        // Then

    }

    @Test
    void passValidate() {

        // Given

        // When
        fieldsValidations.passValidate("a2asfGfdfdf4");

        // Then

    }

    @Test
    void emptyFieldValidate() {

        // Given

        // When
        fieldsValidations.emptyFieldValidate("name", "juan");

        // Then

    }
}