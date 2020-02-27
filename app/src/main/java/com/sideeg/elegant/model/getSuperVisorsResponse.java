package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getSuperVisorsResponse {

    @SerializedName("data")
    private List<SuperVisorData> data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<SuperVisorData> getData() {
        return data;
    }

    public void setData(List<SuperVisorData> data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
