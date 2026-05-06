package com.example.demo.Payloads;

import com.example.demo.Entities.Society;
import com.example.demo.Enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String mobileNumber;

    private UserStatus userStatus;

    private String imageURL;

    private UserRole userRole;

    private LocalDateTime createdAt;

    private Society society;
    private Long societyId;


    // NORMAL USER
    private Long buildingId;
    private Long floorId;
    private Long flatId;
    private NormalUserType normalUserType;

    // STAFF
    private StaffType staffType;
    private StaffLevel staffLevel;
    private String dutyTiming;
    private Double salary;


    private String societyName;
    private String buildingName;
    private String floorNumber;
    private String flatNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String entryCode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String qrCodePath;

    private Double price; // for OWNER
    private Double rent;  // for TENANT

    private Boolean isLiving;

// GETTERS & SETTERS



    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

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

    public Boolean getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(Boolean living) {
        isLiving = living;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
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

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
}
