
package com.careerbridge.controller;

import com.careerbridge.entity.Notification;
import com.careerbridge.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getNotifications(@RequestParam String email) {
        return notificationService.getNotificationsByEmail(email);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/mark-all-read")
    public String markAllRead(@RequestParam String email) {
        notificationService.markAllRead(email);
        return "All notifications marked as read";
    }
}
