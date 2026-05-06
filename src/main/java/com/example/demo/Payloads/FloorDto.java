package com.example.demo.Payloads;

import com.example.demo.Enums.FloorStatus;

import java.util.List;

public class FloorDto {

    private Long id;

    private String floorNumber;

    private FloorStatus floorStatus;


    private Long societyId;
    private Long buildingId;

    private List<FlatDto> flats;

// CONSTRUCTORS
    public FloorDto(Long id, String floorNumber, List<FlatDto> flats) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.flats = flats;
    }

    public FloorDto() {}


// GETTERS & SETTERS


    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public List<FlatDto> getFlats() {
        return flats;
    }

    public void setFlats(List<FlatDto> flats) {
        this.flats = flats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public FloorStatus getFloorStatus() {
        return floorStatus;
    }

    public void setFloorStatus(FloorStatus floorStatus) {
        this.floorStatus = floorStatus;
    }

    public long getSocietyId() {
        return societyId;
    }

    public long getBuildingId() {
        return buildingId;
    }

}
