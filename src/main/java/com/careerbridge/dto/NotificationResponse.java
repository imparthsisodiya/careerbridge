package com.careerbridge.dto;

public class NotificationResponse {
    private Long id;
    private String title;
    private String message;
    private boolean isRead;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, String title, String message, boolean isRead) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
