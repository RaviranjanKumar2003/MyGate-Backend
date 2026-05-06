package com.example.demo.Services;

import com.example.demo.Enums.FlatStatus;
import com.example.demo.Payloads.FlatCountResponse;
import com.example.demo.Payloads.FlatDto;

import java.util.List;

public interface FlatService {

// 1. CREATE FLATS
    FlatDto createFlat(FlatDto dto);


// 2. GET ALL FLATS BY FLOOR
    List<FlatDto> getFlatsByFloor(Long floorId);


// 3.  GET ALL FLATS BY BUILDING
    List<FlatDto> getFlatsByBuilding(Long buildingId);


// 4.  GET ALL FLATS BY SOCIETY
    List<FlatDto> getFlatsBySociety(Long societyId);


// 5. GET ALL FLATS IN A SOCIETY BY STATUS
    List<FlatDto> getFlatsBySocietyAndStatus(Long societyId, FlatStatus status);


    /* ================= COUNT ================= */

// 6. GET FLATS COUNT IN A SOCIETY
    long countFlatsBySocietyAndStatus(
            Long societyId,
            FlatStatus status
    );



// 7. GET FLATS COUNT IN A BUILDING
    long countFlatsByBuildingAndStatus(
            Long societyId,
            Long buildingId,
            FlatStatus status
    );



// 8. GET FLATS COUNT IN A FLOOR
    long countFlatsByFloorAndStatus(
            Long societyId,
            Long buildingId,
            Long floorId,
            FlatStatus status
    );



// 9.  GET FLATS BY FLOOR AND STATUS
    List<FlatDto> getFlatsByFloorAndStatus(Long floorId, FlatStatus status);


// 10.  GET FLATS BY ID
    FlatDto getFlatById(Long flatId);


// 11.  GET TOTAL FLAT COUNT BY FLOOR
    FlatCountResponse getFlatCountByFloor(Long floorId);


// 12.  GET TOTAL FLAT COUNT BY BUILDING
    FlatCountResponse getFlatCountByBuilding(Long buildingId);

// 13.  GET TOTAL FLAT COUNT BY SOCIETY
    FlatCountResponse getFlatCountBySociety(Long societyId);


// 14.  UPDATE FLATS
    FlatDto updateFlat(Long flatId, FlatDto dto);


    void updateFlatStatus(Long flatId, FlatStatus status);

    void blockFlatsBySociety(Long societyId);

    void blockFlatsByBuilding(Long buildingId);

    void blockFlatsByFloor(Long floorId);





// 15.  DELETE FLATS
    void softDeleteFlat(Long flatId); // BLOCK


}
