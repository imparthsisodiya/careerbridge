package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String phone;
    private String location;
    private String degree;
    private String college;
    private String educationYear;
    

    @Column(length = 2000)
    private String about;

    private String linkedIn;
    private String resumeFileName;
    private String resumePath;

    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;

    public User() {
    }

    public User(Long id, String name, String email, String password, Role role, String phone,
                String location, String degree, String college, String educationYear,
                String about, String linkedIn, String resumeFileName, String resumePath,
                String profileImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.location = location;
        this.degree = degree;
        this.college = college;
        this.educationYear = educationYear;
        this.about = about;
        this.linkedIn = linkedIn;
        this.resumeFileName = resumeFileName;
        this.resumePath = resumePath;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getResumeFileName() {
        return resumeFileName;
    }

    public String getResumePath() {
        return resumePath;
    }

    public String getProfileImage() {
        return profileImage;
    }
    public String getSkills() {
    return skills;
    }

    

    public String getBio() {
    return bio;
    }
    
    public void setSkills(String skills) {
    this.skills = skills;
    }

    public void setBio(String bio) {
    this.bio = bio;
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

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
