package com.shyrokovkaya.anastasiia.testapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.shyrokovkaya.anastasiia.testapplication.helpers.DatabaseHelper;

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
                DatabaseHelper.LAST_NAME_COLUMN,
                DatabaseHelper.PHONE_COLUMN,
                DatabaseHelper.EMAIL_COLUMN,
                DatabaseHelper.WEB_COLUMN
        };

        int[] toView = new int[] {
                R.id.name,
                0,
                R.id.phone,
                R.id.email,
                R.id.url
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.user_row,
                cursor,
                fromColumns,
                toView,
                0);

        dataAdapter.setViewBinder(new NameViewBinder());

        ListView userList = (ListView) findViewById(R.id.user_list);
        userList.setAdapter(dataAdapter);
    }

    public void gotoAdd(View view) {
        startActivity(new Intent(UsersActivity.this, RegistrationActivity.class));
    }

    private class NameViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if (view.getId() == R.id.name) {
                int fnColumnIndex = cursor.getColumnIndex(DatabaseHelper.FIRST_NAME_COLUMN);
                int lnColumnIndex = cursor.getColumnIndex(DatabaseHelper.LAST_NAME_COLUMN);
                String name = cursor.getString(fnColumnIndex) + " " + cursor.getString(lnColumnIndex);
                ((TextView) view).setText(name);
                return true;
            }
            return false;
        }
    }
}
