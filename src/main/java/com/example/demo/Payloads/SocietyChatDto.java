package com.example.demo.Payloads;

import com.example.demo.Enums.NormalUserType;
import com.example.demo.Enums.UserRole;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class SocietyChatDto {

    private Long id;
    private Long societyId;
    private Long senderId;
    private String senderName;
    private UserRole role;
    private NormalUserType userType;
    private String message;
    private LocalDateTime createdAt;

    private boolean deletedForEveryone;
    private boolean deletedForMe;
    private boolean edited;

    private Map<String, Long> reactions;

    private boolean seen;

    private Set<Long> seenByUsers;

    private Long tempId;

    private String fileType;

    private Long replyToMessageId;
    private String replyToMessageText;
    private String replyToSenderName;

    private String replyToFileType;


// CONSTRUCTOR

    public SocietyChatDto() {
    }

    public SocietyChatDto(
            Long id,
            Long societyId,
            Long senderId,
            String senderName,
            UserRole role,
            NormalUserType userType,
            String message,
            LocalDateTime createdAt
    ) {
        this.id=id;
        this.societyId = societyId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.role = role;
        this.userType = userType;
        this.message = message;
        this.createdAt = createdAt;
    }


// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Set<Long> getSeenByUsers() {
        return seenByUsers;
    }

    public void setSeenByUsers(Set<Long> seenByUsers) {
        this.seenByUsers = seenByUsers;
    }

    public Long getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(Long replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }

    public String getReplyToFileType() {
        return replyToFileType;
    }

    public void setReplyToFileType(String replyToFileType) {
        this.replyToFileType = replyToFileType;
    }

    public String getReplyToMessageText() {
        return replyToMessageText;
    }

    public void setReplyToMessageText(String replyToMessageText) {
        this.replyToMessageText = replyToMessageText;
    }

    public String getReplyToSenderName() {
        return replyToSenderName;
    }

    public void setReplyToSenderName(String replyToSenderName) {
        this.replyToSenderName = replyToSenderName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Map<String, Long> getReactions() {
        return reactions;
    }

    public void setReactions(Map<String, Long> reactions) {
        this.reactions = reactions;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public boolean isDeletedForEveryone() {
        return deletedForEveryone;
    }

    public void setDeletedForEveryone(boolean deletedForEveryone) {
        this.deletedForEveryone = deletedForEveryone;
    }

    public boolean isDeletedForMe() {
        return deletedForMe;
    }

    public void setDeletedForMe(boolean deletedForMe) {
        this.deletedForMe = deletedForMe;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public NormalUserType getUserType() {
        return userType;
    }

    public void setUserType(NormalUserType userType) {
        this.userType = userType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}