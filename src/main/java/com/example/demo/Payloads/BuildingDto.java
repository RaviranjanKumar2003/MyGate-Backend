package com.example.demo.Payloads;

import com.example.demo.Enums.BuildingStatus;

public class BuildingDto {

    private Long id;

    private String name;

    private Long societyId;

    private BuildingStatus isActive;



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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildingStatus getIsActive() {
        return isActive;
    }

    public void setIsActive(BuildingStatus isActive) {
        this.isActive = isActive;
    }
}
