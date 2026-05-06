package com.example.demo.Payloads;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class VisitorCodeRequestDto {

    private Long userId;       // owner/tenant id
    private String visitorName;   // visitor ka naam

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") // match datetime-local input
    private LocalDateTime expiryTime; // custom expiry

    private Long societyId;

// GETTERS & SETTERS


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}