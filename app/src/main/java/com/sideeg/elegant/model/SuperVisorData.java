package com.sideeg.elegant.model;

public class SuperVisorData {

    private String supervisorName;
    private String supervisorPhone;

    public SuperVisorData(String supervisorName, String supervisorPhone) {
        this.supervisorName = supervisorName;
        this.supervisorPhone = supervisorPhone;
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
