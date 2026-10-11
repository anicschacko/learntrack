package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {
    private final int id;
    private final int studentId;
    private final int courseId;
    private final String enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public String getEnrollmentDate() { return enrollmentDate; }
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public String toString() {
        return  " Enrollment Id: " + getId() +
                " Student Id: " + getStudentId() +
                " Course Id: " + getCourseId() +
                " Enrollment Date: " + getEnrollmentDate() +
                " Status: " + getStatus();
    }
}
