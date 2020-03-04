package com.sideeg.elegant.utiltiy;

import com.sideeg.elegant.model.SchoolData;
import com.sideeg.elegant.model.StudentData;
import com.sideeg.elegant.model.SuperVisorData;

public  class StaticElemaents {
    private static SuperVisorData superVisorData;
    private static SchoolData schoolData;
    private static StudentData studentData;

    public static StudentData getStudentData() {
        return studentData;
    }

    public static void setStudentData(StudentData studentData) {
        StaticElemaents.studentData = studentData;
    }

    public static SchoolData getSchoolData() {
        return schoolData;
    }

    public static void setSchoolData(SchoolData schoolData) {
        StaticElemaents.schoolData = schoolData;
    }

    public static SuperVisorData getSuperVisorData() {
        return superVisorData;
    }

    public static void setSuperVisorData(SuperVisorData superVisorData) {
        StaticElemaents.superVisorData = superVisorData;
    }
}
