package com.careerbridge.dto;

public class SkillRequest {
    private String name;
    private String level;
    private String studentEmail;

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
