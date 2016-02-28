package com.shyrokovkaya.anastasiia.testapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.shyrokovkaya.anastasiia.testapplication.helpers.DatabaseHelper;
import com.shyrokovkaya.anastasiia.testapplication.helpers.StringUtils;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle(R.string.user_list_title);

        displayUserList();
    }

    private void displayUserList() {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        Cursor cursor = mDatabaseHelper.fetchAllUsers();

        String[] fromColumns = new String[] {
                DatabaseHelper.FIRST_NAME_COLUMN,
                DatabaseHelper.LAST_NAME_COLUMN,
                DatabaseHelper.PHONE_COLUMN,
                DatabaseHelper.EMAIL_COLUMN,
                DatabaseHelper.URL_COLUMN
        };

        int[] toView = new int[] {
                R.id.name,
                0,
                R.id.phone,
                R.id.email,
                R.id.url
        };

        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                this, R.layout.user_row,
                cursor,
                fromColumns,
                toView,
                0);

        // full name bind
        dataAdapter.setViewBinder(new NameViewBinder());

        ListView userList = (ListView) findViewById(R.id.user_list);
        userList.setAdapter(dataAdapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                int idColumnIndex = cursor.getColumnIndex(DatabaseHelper._ID);
                gotoDetails(cursor.getInt(idColumnIndex));
            }
        });
    }

    public void gotoAdd(View view) {
        startActivity(new Intent(UserListActivity.this, RegistrationActivity.class));
    }

    public void gotoDetails(int id) {
        Intent intent = new Intent(UserListActivity.this, UserDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class NameViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if (view.getId() == R.id.name) {
                int fnColumnIndex = cursor.getColumnIndex(DatabaseHelper.FIRST_NAME_COLUMN);
                int lnColumnIndex = cursor.getColumnIndex(DatabaseHelper.LAST_NAME_COLUMN);
                String name = StringUtils.capitalize(cursor.getString(fnColumnIndex)) + " "
                        + StringUtils.capitalize(cursor.getString(lnColumnIndex));
                ((TextView) view).setText(name);
                return true;
            }
            return false;
        }
    }
}
