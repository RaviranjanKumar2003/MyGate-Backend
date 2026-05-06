package com.example.demo.Controllers;

import com.example.demo.Enums.FloorStatus;
import com.example.demo.Payloads.FloorDto;
import com.example.demo.Payloads.FloorSummaryDto;
import com.example.demo.Services.FloorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }


// CREATE FLOOR
    @PostMapping("/society/{societyId}/building/{buildingId}/create")
    public ResponseEntity<FloorDto> createFloor(
            @PathVariable Long societyId,
            @PathVariable Long buildingId,
            @RequestBody FloorDto dto) {

        dto.setSocietyId(societyId);
        dto.setBuildingId(buildingId);

        return ResponseEntity.ok(floorService.createFloor(dto));
    }


// GET ALL FLOORS IN A BUILDING

    @GetMapping("/society/{societyId}/building/{buildingId}/get")
    public ResponseEntity<List<FloorDto>> getFloorsByBuilding(
            @PathVariable Long societyId,
            @PathVariable Long buildingId) {

        return ResponseEntity.ok(
                floorService.getFloorsByBuildingAndSociety(societyId, buildingId));
    }


// GET FLOORS BY BUILDING + STATUS
    @GetMapping("/society/{societyId}/building/{buildingId}/status/{status}")
    public ResponseEntity<List<FloorDto>> getFloorsByBuildingAndStatus(
            @PathVariable Long buildingId,
            @PathVariable FloorStatus status) {

        return ResponseEntity.ok(
                floorService.getFloorsByBuildingAndStatus(buildingId, status));
    }



// GET FLOORS BY SOCIETY + STATUS
    @GetMapping("/society/{societyId}/status/{status}")
    public ResponseEntity<List<FloorDto>> getFloorsBySocietyAndStatus(
            @PathVariable Long societyId,
            @PathVariable FloorStatus status) {

        return ResponseEntity.ok(
                floorService.getFloorsBySocietyAndStatus(societyId, status));
    }


// BUILDING FLOOR SUMMARY
    @GetMapping("/society/{societyId}/building/{buildingId}/summary")
    public ResponseEntity<FloorSummaryDto> getBuildingSummary(
            @PathVariable Long buildingId) {

        return ResponseEntity.ok(
                floorService.getBuildingFloorSummary(buildingId));
    }


// SOCIETY FLOOR SUMMARY
    @GetMapping("/society/{societyId}/summary")
    public ResponseEntity<FloorSummaryDto> getSocietySummary(
            @PathVariable Long societyId) {

        return ResponseEntity.ok(
                floorService.getSocietyFloorSummary(societyId));
    }


// UPDATE FLOOR
    @PutMapping("/society/{societyId}/building/{buildingId}/floor/{floorId}")
    public ResponseEntity<FloorDto> updateFloor(
            @PathVariable Long floorId,
            @RequestBody FloorDto dto) {

        return ResponseEntity.ok(
                floorService.updateFloor(floorId, dto));
    }



// DELETE FLOOR (SOFT)
    @DeleteMapping("/society/{societyId}/building/{buildingId}/floor/{floorId}/delete")
    public ResponseEntity<String> deleteFloor(@PathVariable Long floorId) {
        floorService.deleteFloor(floorId);
        return ResponseEntity.ok("Floor marked as IN");
    }
}
