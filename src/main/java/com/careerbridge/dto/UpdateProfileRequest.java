package com.careerbridge.dto;

public class UpdateProfileRequest {

    private String name;
    private String email;
    private String phone;
    private String location;
    private String degree;
    private String college;
    private String educationYear;
    private String about;
    private String linkedIn;
    private String profileImage;
    private String resumeFileName;
    private String resumePath;

    public UpdateProfileRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getDegree() {
        return degree;
    }

    public String getCollege() {
        return college;
    }

    public String getEducationYear() {
        return educationYear;
    }

    public String getAbout() {
        return about;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setEducationYear(String educationYear) {
        this.educationYear = educationYear;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }
}
