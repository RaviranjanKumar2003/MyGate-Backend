package com.example.demo.Services;

import com.example.demo.Payloads.NotificationDto;

import java.util.List;

public interface NotificationService {


// CREATE NOTIFICATION
    void createNotification(NotificationDto dto);


// GET NOTIFICATION FOR SOCIETY ADMIN
    List<NotificationDto> getNotificationsForAdmin( Long societyId, Long adminId);

    // Fetch notifications for any user based on role and societyId
    List<NotificationDto> getNotificationsForUser(Long societyId, String userRole);

    void markAsRead(Long notificationId);

    void deleteNotification(Long notificationId);
}
