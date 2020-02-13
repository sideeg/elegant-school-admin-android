package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

public class SchoolData {

    @SerializedName("id")
    private int id;
    @SerializedName("schoolname")
    private String schoolName;
    @SerializedName("name")
    private String mangerName;
    @SerializedName("mangerPhone")
    private String mangerPhone;

    @SerializedName("password")
    private String mangerPassowrd;

    @SerializedName("api_token")
    private String api_token;

    public SchoolData(String schoolName, String mangerName, String mangerPhone, String mangerPassowrd) {
        this.schoolName = schoolName;
        this.mangerName = mangerName;
        this.mangerPhone = mangerPhone;
        this.mangerPassowrd = mangerPassowrd;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMangerName() {
        return mangerName;
    }

    public void setMangerName(String mangerName) {
        this.mangerName = mangerName;
    }

    public String getMangerPhone() {
        return mangerPhone;
    }

    public void setMangerPhone(String mangerPhone) {
        this.mangerPhone = mangerPhone;
    }

    public String getMangerPassowrd() {
        return mangerPassowrd;
    }

    public void setMangerPassowrd(String mangerPassowrd) {
        this.mangerPassowrd = mangerPassowrd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
