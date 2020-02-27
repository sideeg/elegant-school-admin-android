package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getParentResponse {

    @SerializedName("data")
    private List<ParentData> data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<ParentData> getData() {
        return data;
    }

    public void setData(List<ParentData> data) {
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
