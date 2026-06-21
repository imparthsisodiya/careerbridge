package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String issuer;
    private String issueDate;
    private String credentialId;
    private String credentialUrl;
    private String studentEmail;

    public Certification() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public void setCredentialUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
