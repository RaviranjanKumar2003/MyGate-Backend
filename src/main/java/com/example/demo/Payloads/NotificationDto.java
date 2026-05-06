package com.example.demo.Payloads;

import com.example.demo.Enums.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {

    private Long id;
    private String title;
    private String message;

    private NotificationType type;

    private Long societyId;
    private Long receiverAdminId;

    private boolean isRead;

    private LocalDateTime createdAt;

    private Long targetSocietyId; // null = global
    private String targetRole;
    private Long referenceId;


// GETTERS & SETTERS


    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public Long getReceiverAdminId() {
        return receiverAdminId;
    }

    public void setReceiverAdminId(Long receiverAdminId) {
        this.receiverAdminId = receiverAdminId;
    }

    public Long getTargetSocietyId() {
        return targetSocietyId;
    }

    public void setTargetSocietyId(Long targetSocietyId) {
        this.targetSocietyId = targetSocietyId;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
