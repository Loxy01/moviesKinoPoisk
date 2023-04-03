package com.loxy01.test_start_new_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Watchability {

    @SerializedName("items")
    @Expose
    private Object items;

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }
}