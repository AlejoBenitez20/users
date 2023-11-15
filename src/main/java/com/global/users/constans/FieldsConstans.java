package com.global.users.constans;

public class FieldsConstans {

    public FieldsConstans() {
    }

    public static final String EMAIL = "Email";

    public static final String PASSWORD = "Password";

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?!.*[A-Z].*[A-Z])(?=.*\\d.*\\d)(?=.*[a-zA-Z\\d]).{8,12}$";

}
