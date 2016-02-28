package com.shyrokovkaya.anastasiia.testapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.shyrokovkaya.anastasiia.testapplication.helpers.DatabaseHelper;
import com.shyrokovkaya.anastasiia.testapplication.helpers.StringUtils;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        setTitle(R.string.user_details_title);

        Bundle bundle = getIntent().getExtras();
        displayUser(bundle.getInt("user_id"));
    }

    private void displayUser(int userId) {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        Cursor cursor = mDatabaseHelper.fetchUserById(userId);
        if (cursor == null) {
            gotoList(null);
        } else {
            int fnColumnIndex = cursor.getColumnIndex(DatabaseHelper.FIRST_NAME_COLUMN);
            int lnColumnIndex = cursor.getColumnIndex(DatabaseHelper.LAST_NAME_COLUMN);
            int phColumnIndex = cursor.getColumnIndex(DatabaseHelper.PHONE_COLUMN);
            int emColumnIndex = cursor.getColumnIndex(DatabaseHelper.EMAIL_COLUMN);
            int urlColumnIndex = cursor.getColumnIndex(DatabaseHelper.URL_COLUMN);

            TextView firstName = (TextView) findViewById(R.id.first_name);
            TextView lastName = (TextView) findViewById(R.id.last_name);
            TextView phone = (TextView) findViewById(R.id.phone);
            TextView email = (TextView) findViewById(R.id.email);
            TextView url = (TextView) findViewById(R.id.url);

            firstName.setText(StringUtils.capitalize(cursor.getString(fnColumnIndex)));
            lastName.setText(StringUtils.capitalize(cursor.getString(lnColumnIndex)));
            phone.setText(cursor.getString(phColumnIndex));
            email.setText(cursor.getString(emColumnIndex));
            url.setText(cursor.getString(urlColumnIndex));
        }
    }

    public void gotoList(View view) {
        startActivity(new Intent(UserDetailsActivity.this, UserListActivity.class));
    }
}
