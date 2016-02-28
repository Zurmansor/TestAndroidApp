package com.shyrokovkaya.anastasiia.testapplication.validator;

import android.util.Patterns;
import android.widget.EditText;

public class Validator {

    private final String NAME_REGEXP = "^[\\p{L} .'-]+$";

    public boolean validate(EditText field, Type validatorType) {
        switch (validatorType) {
            case NAME:
                return validateName(field);
            case EMAIL:
                return validateEmail(field);
            case PHONE:
                return validatePhone(field);
            case URL:
                return validateUrl(field);
            default:
                return true;
        }
    }

    private boolean validateName(EditText field) {
        if (!isValidName(field.getText().toString())) {
            field.setError(ErrorMessage.INVALID_NAME.toString());
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhone(EditText field) {
        if (!isValidPhone(field.getText().toString())) {
            field.setError(ErrorMessage.INVALID_PHONE.toString());
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail(EditText field) {
        if (!isValidEmail(field.getText().toString())) {
            field.setError(ErrorMessage.INVALID_EMAIL.toString());
            return false;
        } else {
            return true;
        }
    }

    private boolean validateUrl(EditText field) {
        if (!isValidUrl(field.getText().toString())) {
            field.setError(ErrorMessage.INVALID_URL.toString());
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidName(String name) {
        return name.matches(NAME_REGEXP);
    }

    private boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

}
