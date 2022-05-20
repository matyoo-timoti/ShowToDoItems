package com.asterisk.showtodoitems;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listView = findViewById(R.id.taskListView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);

        ArrayList<ListModel> list = new ArrayList<>(fetchTasks());

        ListAdapter adapter = new ListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @SuppressLint("Recycle")
    public ArrayList<ListModel> fetchTasks() {
        ArrayList<ListModel> listOfTasks = new ArrayList<>();

        Uri allTasks = Uri.parse("content://com.asterisk.mytodo.PROVIDER/todo");
        String COLUMN_ID = "_id";
        String COLUMN_TASK = "task";
        String COLUMN_STAT = "status";
//        ContentProviderClient yourCR = getContentResolver().acquireContentProviderClient(allTasks);
        CursorLoader cursorLoader = new CursorLoader(getBaseContext(), allTasks, null, null, null, "_id");
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String task = cursor.getString(cursor.getColumnIndex(COLUMN_TASK));
                @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(COLUMN_STAT));
                listOfTasks.add(new ListModel(id, task, status));
            }
        } else {

            Toast.makeText(this, "Null", Toast.LENGTH_LONG).show();
        }
        return listOfTasks;
    }
}