package com.sideeg.elegant.model;

import androidx.annotation.NonNull;

public class StudentData {

   private String studentName;
   private String studentClassName;
   private String studentSupervisorName;

    public String getStudentName() {
        return studentName;
    }

    public StudentData(String studentName, String studentClassName, String studentSupervisorName) {
        this.studentName = studentName;
        this.studentClassName = studentClassName;
        this.studentSupervisorName = studentSupervisorName;
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

    public String getStudentSupervisorName() {
        return studentSupervisorName;
    }

    public void setStudentSupervisorName(String studentSupervisorName) {
        this.studentSupervisorName = studentSupervisorName;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
