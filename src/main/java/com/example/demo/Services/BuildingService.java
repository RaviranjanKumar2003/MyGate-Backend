package com.example.demo.Services;

import com.example.demo.Payloads.BuildingDto;

import java.util.List;

public interface BuildingService {

// CREATE BUILDING

    BuildingDto createBuilding(Long societyId, BuildingDto buildingDto);

// GET ALL PENDING BUILDING

    List<BuildingDto> getAllActiveBuildings(Long societyId);

// GET ALL DE-PENDING BUILDING

    List<BuildingDto> getAllDeActiveBuildings(Long societyId);

// GET BUILDING BY ID

    BuildingDto getBuildingById(Long buildingId);

// UPDATE BUILDING

    BuildingDto updateBuilding(BuildingDto dto, Long buildingId);

// DELETE BUILDING(SOFT DELETE)

    void deleteBuilding(Long buildingId);

// SEARCH BUILDING
    List<BuildingDto> searchBuildingByName(String keyword);

}
