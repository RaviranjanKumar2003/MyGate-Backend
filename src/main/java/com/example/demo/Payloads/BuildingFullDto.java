package com.example.demo.Payloads;

import com.example.demo.Payloads.FloorDto;

import java.util.List;

public class BuildingFullDto {

    private Long id;
    private String name;
    private List<FloorDto> floors;


// CONSTRUCTOR

    public BuildingFullDto(Long id, String name, List<FloorDto> floors) {
        this.id = id;
        this.name = name;
        this.floors = floors;
    }

// GETTERS & SETTERS


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

    public List<FloorDto> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorDto> floors) {
        this.floors = floors;
    }
}