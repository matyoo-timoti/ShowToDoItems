package com.asterisk.showtodoitems;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView = findViewById(R.id.taskListView);
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);

        getSupportLoaderManager().initLoader(1, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri allTasks = Uri.parse("content://com.asterisk.mytodo.PROVIDER/todo");
        return new CursorLoader(getBaseContext(), allTasks, null, null, null, "_id");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        ArrayList<ListModel> list = new ArrayList<>();
        String COLUMN_ID = "_id";
        String COLUMN_TASK = "task";
        String COLUMN_STAT = "status";

        while (data.moveToNext()) {
            @SuppressLint("Range") String id = data.getString(data.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String task = data.getString(data.getColumnIndex(COLUMN_TASK));
            @SuppressLint("Range") String status = data.getString(data.getColumnIndex(COLUMN_STAT));
            list.add(new ListModel(id, task, status));
        }

        ListAdapter adapter = new ListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}