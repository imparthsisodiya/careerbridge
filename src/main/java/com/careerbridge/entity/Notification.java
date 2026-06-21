
package com.careerbridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @Column(columnDefinition = "TEXT")
    private String title;

    private String timeText;

    private String status;

    public Notification() {
    }

    public Notification(Long id, String userEmail, String title, String timeText, String status) {
        this.id = id;
        this.userEmail = userEmail;
        this.title = title;
        this.timeText = timeText;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeText() {
        return timeText;
    }

    public String getStatus() {
        return status;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}