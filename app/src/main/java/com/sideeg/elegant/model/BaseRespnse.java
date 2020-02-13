package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

public class BaseRespnse {

    @SerializedName("data")
    private Object data;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setData(Object data){
        this.data = data;
    }

    public Object getData(){
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