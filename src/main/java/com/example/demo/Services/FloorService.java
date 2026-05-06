package com.example.demo.Services;

import com.example.demo.Enums.FloorStatus;
import com.example.demo.Payloads.FloorDto;
import com.example.demo.Payloads.FloorSummaryDto;

import java.util.List;

public interface FloorService {

// CREATE FLOOR

    FloorDto createFloor(FloorDto dto);

// GET FLOORS IN A BUILDING

    List<FloorDto> getFloorsByBuilding(Long buildingId);

// GET FLOORS BY BUILDING + STATUS

    List<FloorDto> getFloorsByBuildingAndStatus(Long buildingId, FloorStatus status);

// GET FLOORS BY SOCIETY + STATUS

    List<FloorDto> getFloorsBySocietyAndStatus(Long societyId, FloorStatus status);


//  SUMMARY APIs
    // Building Summary
    FloorSummaryDto getBuildingFloorSummary(Long buildingId);
    // Society Summary
    FloorSummaryDto getSocietyFloorSummary(Long societyId);


// UPDATE FLOOR

    FloorDto updateFloor(Long floorId, FloorDto dto);

// DELETE FLOOR (SOFT DELETE)

    void deleteFloor(Long floorId);

    List<FloorDto> getFloorsByBuildingAndSociety(Long societyId, Long buildingId);
}
