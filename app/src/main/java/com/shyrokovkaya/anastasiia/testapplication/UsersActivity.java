package com.shyrokovkaya.anastasiia.testapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class UsersActivity extends AppCompatActivity {

    private DatabaseHelper mDatabaseHelper;
    private SimpleCursorAdapter dataAdapter;

    private ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        setTitle("User list");

        displayUserList();
    }

    private void displayUserList() {
        mDatabaseHelper = new DatabaseHelper(this);
        Cursor cursor = mDatabaseHelper.fetchAllUsers();

        String[] fromColumns = new String[] {
                DatabaseHelper.FIRST_NAME_COLUMN,
                DatabaseHelper.LAST_NAME_COLUMN
        };

        int[] toView = new int[] {
                R.id.first_name,
                R.id.last_name
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.user_row,
                cursor,
                fromColumns,
                toView,
                0);

        ListView userList = (ListView) findViewById(R.id.user_list);
        userList.setAdapter(dataAdapter);
    }

    public void gotoAdd(View view) {
        startActivity(new Intent(UsersActivity.this, RegistrationActivity.class));
    }
}
