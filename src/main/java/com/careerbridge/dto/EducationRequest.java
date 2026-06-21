package com.careerbridge.dto;

public class EducationRequest {
    private String collegeName;
    private String degree;
    private String fieldOfStudy;
    private String startYear;
    private String endYear;
    private String grade;
    private String studentEmail;

    public String getCollegeName() {
        return collegeName;
    }

    public String getDegree() {
        return degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public String getStartYear() {
        return startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public String getGrade() {
        return grade;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
