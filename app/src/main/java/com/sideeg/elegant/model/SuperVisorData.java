package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

public class SuperVisorData {

    @SerializedName("name")
    private String supervisorName;

    @SerializedName("phone")
    private String supervisorPhone;

    @SerializedName("id")
    private String supervisor_id;

    @SerializedName("discission")
    private String discission;

    @SerializedName("schoolid")
    private String schoolid;

    @SerializedName("password")
    private String password;

    @SerializedName("api_token")
    private String api_token;

    public SuperVisorData(String supervisorName, String supervisorPhone) {
        this.supervisorName = supervisorName;
        this.supervisorPhone = supervisorPhone;
    }

    public SuperVisorData(String supervisorName, String supervisorPhone, String supervisor_id, String discission, String schoolid, String password, String api_token) {
        this.supervisorName = supervisorName;
        this.supervisorPhone = supervisorPhone;
        this.supervisor_id = supervisor_id;
        this.discission = discission;
        this.schoolid = schoolid;
        this.password = password;
        this.api_token = api_token;
    }

    public String getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(String supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public String getDiscission() {
        return discission;
    }

    public void setDiscission(String discission) {
        this.discission = discission;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }
}
