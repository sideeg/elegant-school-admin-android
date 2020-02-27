package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getUsersResponse {

    @SerializedName("data")
    private List<SchoolData> data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<SchoolData> getData() {
        return data;
    }

    public void setData(List<SchoolData> data) {
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
