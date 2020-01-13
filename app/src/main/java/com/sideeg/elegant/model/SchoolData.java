package com.sideeg.elegant.model;

public class SchoolData {

    private String schoolName;
    private String mangerName;
    private String mangerPhone;
    private String mangerPassowrd;

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
}
