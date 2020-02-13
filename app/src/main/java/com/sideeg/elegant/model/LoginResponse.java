package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("data")
    private SchoolData data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setData(SchoolData data){
        this.data = data;
    }

    public SchoolData getData(){
        return data;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public boolean isError(){
        return error;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "BaseResponse{" +
                        "data = '" + data + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
