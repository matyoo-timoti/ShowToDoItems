package com.asterisk.showtodoitems;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    TextView id, task, status;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.tv_id);
        task = itemView.findViewById(R.id.tv_task);
        status = itemView.findViewById(R.id.tv_stat);
    }
}
