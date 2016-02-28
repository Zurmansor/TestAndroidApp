package com.shyrokovkaya.anastasiia.testapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.shyrokovkaya.anastasiia.testapplication.helpers.DatabaseHelper;
import com.shyrokovkaya.anastasiia.testapplication.model.User;
import com.shyrokovkaya.anastasiia.testapplication.validator.Type;
import com.shyrokovkaya.anastasiia.testapplication.validator.Validator;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void submit(View view) {
        User user = new User();
        Validator validator = new Validator(this);

        trimAllFields();

        EditText firstNameField = (EditText) findViewById(R.id.first_name);
        EditText lastNameField = (EditText) findViewById(R.id.last_name);
        EditText phoneField = (EditText) findViewById(R.id.phone);
        EditText emailField = (EditText) findViewById(R.id.email);
        EditText urlField = (EditText) findViewById(R.id.web);

        boolean isValid = true;
        if (!validator.validate(firstNameField, Type.NAME)) {
            isValid = false;
        }
        if (!validator.validate(lastNameField, Type.NAME)) {
            isValid = false;
        }
        if (!validator.validate(phoneField, Type.PHONE)) {
            isValid = false;
        }
        if (!validator.validate(emailField, Type.EMAIL)) {
            isValid = false;
        }
        if (!validator.validate(urlField, Type.URL)) {
            isValid = false;
        }

        if (isValid) {
            user.setFirstName(firstNameField.getText().toString());
            user.setLastName(lastNameField.getText().toString());
            user.setPhone(phoneField.getText().toString());
            user.setEmail(emailField.getText().toString());
            user.setWeb(urlField.getText().toString());

            DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
            if (mDatabaseHelper.addUser(user)) {
                startActivity(new Intent(RegistrationActivity.this, UsersActivity.class));
            }
        }

    }

    private void trimAllFields() {
        trimField(R.id.first_name);
        trimField(R.id.last_name);
        trimField(R.id.phone);
        trimField(R.id.email);
        trimField(R.id.web);
    }

    private void trimField(int id) {
        EditText field = (EditText) findViewById(id);
        field.setText(field.getText().toString().trim());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
