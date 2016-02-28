package com.shyrokovkaya.anastasiia.testapplication.validator;

import android.content.Context;
import android.content.res.Resources;
import android.util.Patterns;
import android.widget.EditText;

import com.shyrokovkaya.anastasiia.testapplication.R;

public class Validator {

    private final Resources resources;

    public Validator(Context context) {
        this.resources = context.getResources();
    }

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
            field.setError(resources.getString(R.string.invalid_name));
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhone(EditText field) {
        if (!isValidPhone(field.getText().toString())) {
            field.setError(resources.getString(R.string.invalid_phone));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail(EditText field) {
        if (!isValidEmail(field.getText().toString())) {
            field.setError(resources.getString(R.string.invalid_email));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateUrl(EditText field) {
        if (!isValidUrl(field.getText().toString())) {
            field.setError(resources.getString(R.string.invalid_url));
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidName(String name) {
        String nameRegexp = "^[\\p{L} .'-]+$";
        return name.matches(nameRegexp);
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
