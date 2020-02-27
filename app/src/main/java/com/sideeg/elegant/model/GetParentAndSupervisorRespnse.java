package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetParentAndSupervisorRespnse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("parent")
    private List<ParentData> parent;

    @SerializedName("supervisor")
    private List<SuperVisorData> supervisor;

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

    public List<ParentData> getParent() {
        return parent;
    }

    public void setParent(List<ParentData> parent) {
        this.parent = parent;
    }

    public List<SuperVisorData> getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(List<SuperVisorData> supervisor) {
        this.supervisor = supervisor;
    }
}
