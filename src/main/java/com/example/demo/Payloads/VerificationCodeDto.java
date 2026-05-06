package com.example.demo.Payloads;

import java.time.LocalDateTime;

public class VerificationCodeDto {

    private Long id;
    private String code;
    private LocalDateTime expiryTime;
    private boolean used;
    private Long userId;

    private String visitorName;


// Constructors
    public VerificationCodeDto() {}

    public VerificationCodeDto(Long id, String code, LocalDateTime expiryTime, boolean used, Long userId, String visitorName) {
        this.id = id;
        this.code = code;
        this.expiryTime = expiryTime;
        this.used = used;
        this.userId = userId;
        this.visitorName = visitorName;
    }

// Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDateTime getExpiryTime() { return expiryTime; }
    public void setExpiryTime(LocalDateTime expiryTime) { this.expiryTime = expiryTime; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

}