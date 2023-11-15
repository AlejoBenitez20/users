package com.global.users.utils;

import com.global.users.exception.UserExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.global.users.constans.FieldsConstans.EMAIL_REGEX;
import static com.global.users.constans.FieldsConstans.PASSWORD_REGEX;

@Component
public class FieldsValidations {


    public void emailValidate(String email) {

        if (!getMatcher(email, EMAIL_REGEX).matches())
            throw new UserExceptions( HttpStatus.BAD_REQUEST, "Invalid email field.", 400);

    }

    public void passValidate(String pass) {

        if (!getMatcher(pass, PASSWORD_REGEX).matches())
            throw new UserExceptions( HttpStatus.BAD_REQUEST, "Invalid pass field.", 400);

    }

    public void emptyFieldValidate(String fieldName, String value){

        if(Objects.isNull(value) || value.isEmpty())
            throw new UserExceptions( HttpStatus.BAD_REQUEST, "Field "+fieldName+" should not be null.", 400);

    }

    private static Matcher getMatcher(String email, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher;
    }



}
