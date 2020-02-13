package com.sideeg.elegant.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class StudentData {

    @SerializedName("id")
   private String id;

    @SerializedName("name")
   private String studentName;

    @SerializedName("api_token")
   private String studentClassName;

    @SerializedName("supervisor")
   private SuperVisorData studentSupervisor;

    @SerializedName("parent")
   private ParentData parent;

    @SerializedName("user")
   private SchoolData schoolData ;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lan")
    private String lan;

    public String getStudentName() {
        return studentName;
    }

    public StudentData(String studentName, String studentClassName, SuperVisorData studentSupervisor) {
        this.studentName = studentName;
        this.studentClassName = studentClassName;
        this.studentSupervisor = studentSupervisor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParentData getParent() {
        return parent;
    }

    public void setParent(ParentData parent) {
        this.parent = parent;
    }

    public SchoolData getSchoolData() {
        return schoolData;
    }

    public void setSchoolData(SchoolData schoolData) {
        this.schoolData = schoolData;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
    }

    public SuperVisorData getStudentSupervisor() {
        return studentSupervisor;
    }

    public void setStudentSupervisor(SuperVisorData studentSupervisor) {
        this.studentSupervisor = studentSupervisor;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
