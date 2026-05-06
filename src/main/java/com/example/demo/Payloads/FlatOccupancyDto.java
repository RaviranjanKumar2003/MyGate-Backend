package com.example.demo.Payloads;

import com.example.demo.Enums.NormalUserType;
import java.time.LocalDate;

public class FlatOccupancyDto {

    private Long id;
    private Long flatId;
    private Long userId;

    private NormalUserType type;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean active;
    private Boolean isLiving;

// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getLiving() {
        return isLiving;
    }

    public void setLiving(Boolean living) {
        isLiving = living;
    }

    public NormalUserType getType() {
        return type;
    }

    public void setType(NormalUserType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(Boolean isLiving) {
        this.isLiving = isLiving;
    }
}