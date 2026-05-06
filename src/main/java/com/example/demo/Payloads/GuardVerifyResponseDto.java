package com.example.demo.Payloads;

public class GuardVerifyResponseDto {
    private boolean success;       // Entry allowed ya nahi
    private String message;        // Status message
    private Long userId;        // Optional: user details
    private String userName;       // Optional: user details
    private String temporaryCode;  // 5-minute code


// GETTERS & SETTERS


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTemporaryCode() {
        return temporaryCode;
    }

    public void setTemporaryCode(String temporaryCode) {
        this.temporaryCode = temporaryCode;
    }
}