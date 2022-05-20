package com.asterisk.showtodoitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private final Context context;
    private final ArrayList<ListModel> listOfTasks;

    ListAdapter(Context context, ArrayList<ListModel> listOfTasks) {
        this.context = context;
        this.listOfTasks = listOfTasks;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_layout, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListModel model = listOfTasks.get(position);
        holder.id.setText(model.get_id());
        holder.task.setText(model.getTask());
        holder.status.setText(model.getStatus());
    }

    @Override
    public int getItemCount() {
        return listOfTasks.size();
    }
}
