package com.asterisk.showtodoitems;

public class ListModel {
    private final String _id;
    private final String status;
    private final String task;

    public ListModel(String _id, String task, String status) {
        this._id = _id;
        this.task = task;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public String getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }
}
