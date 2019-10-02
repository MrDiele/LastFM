package com.example.lastfm.core.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageItem {
    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("size")
    @Expose
    private String size;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}