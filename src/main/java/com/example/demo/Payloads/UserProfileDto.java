package com.example.demo.Payloads;

import com.example.demo.Enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileDto {

    // Common fields for all users
    private Long id;
    private String name;
    private String email;
    private String imageURL;
    private String password;
    private String mobileNumber;
    private UserStatus userStatus;
    private UserRole userRole;
    private LocalDateTime createdAt;

    // NORMAL_USER specific
    private Long buildingId;
    private Long floorId;
    private Long flatId;
    private NormalUserType normalUserType;

    // STAFF specific
    private StaffType staffType;
    private StaffLevel staffLevel;
    private String dutyTiming;
    private Double salary;

// Getters & Setters


    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public NormalUserType getNormalUserType() {
        return normalUserType;
    }

    public void setNormalUserType(NormalUserType normalUserType) {
        this.normalUserType = normalUserType;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public StaffLevel getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(StaffLevel staffLevel) {
        this.staffLevel = staffLevel;
    }

    public String getDutyTiming() {
        return dutyTiming;
    }

    public void setDutyTiming(String dutyTiming) {
        this.dutyTiming = dutyTiming;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
