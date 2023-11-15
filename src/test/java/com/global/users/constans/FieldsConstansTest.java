package com.global.users.constans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldsConstansTest {

    @Test
    public void testEmailConstant() {
        assertEquals("Email",  FieldsConstans.EMAIL);
    }

    @Test
    public void testPasswordConstant() {
        assertEquals( "Password", FieldsConstans.PASSWORD);
    }

    @Test
    public void testEmailRegexConstant() {
        assertEquals( "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", FieldsConstans.EMAIL_REGEX);
    }

    @Test
    public void testPasswordRegexConstant() {
        assertEquals("^(?=.*[A-Z])(?!.*[A-Z].*[A-Z])(?=.*\\d.*\\d)(?=.*[a-zA-Z\\d]).{8,12}$", FieldsConstans.PASSWORD_REGEX);
    }

}