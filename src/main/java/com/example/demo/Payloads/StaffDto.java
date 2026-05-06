package com.example.demo.Payloads;

import com.example.demo.Enums.StaffLevel;
import com.example.demo.Enums.StaffStatus;
import com.example.demo.Enums.StaffType;

public class StaffDto {

    private Long id;

    private Long userId;

    private String mobileNumber;

    private StaffLevel staffLevel;

    private StaffType staffType;

    private String dutyTiming;

    private double salary;

    private StaffStatus staffStatus;



// GETTERS & SETTERS


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

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
