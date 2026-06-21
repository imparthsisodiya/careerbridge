package com.careerbridge.dto;

import com.careerbridge.entity.Role;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String phone;
    private String location;
    private String skills;
    private String bio;
    private String profileImage;
    private String resumeFileName;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String email, Role role, String phone,
                        String location, String skills, String bio,
                        String profileImage, String resumeFileName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.location = location;
        this.skills = skills;
        this.bio = bio;
        this.profileImage = profileImage;
        this.resumeFileName = resumeFileName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getSkills() {
        return skills;
    }

    public String getBio() {
        return bio;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }
}