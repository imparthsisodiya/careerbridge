package com.careerbridge.service;

import com.careerbridge.entity.Notification;
import com.careerbridge.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByEmail(String email) {
        return notificationRepository.findByUserEmail(email);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void markAllRead(String email) {
        List<Notification> notifications = notificationRepository.findByUserEmail(email);

        for (Notification notification : notifications) {
            notification.setStatus("READ");
        }

        notificationRepository.saveAll(notifications);
    }
}