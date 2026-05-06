package com.example.demo.Repositories;

import com.example.demo.Entities.*;
import com.example.demo.Enums.FlatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlatRepository extends JpaRepository<Flat, Long> {

    List<Flat> findByFloorId(Long floorId);

    List<Flat> findByBuildingId(Long buildingId);

    List<Flat> findBySocietyId(Long societyId);

    List<Flat> findByFloorIdAndFlatStatus(Long floorId, FlatStatus status);

    boolean existsBySocietyAndBuildingAndFloorAndFlatNumber(
            Society society,
            Building building,
            Floor floor,
            String flatNumber
    );


    List<Flat> findBySocietyIdAndFlatStatus(Long societyId, FlatStatus flatStatus);

    // ✅ SOCIETY LEVEL
    long countBySocietyIdAndFlatStatus(Long societyId, FlatStatus status);

    // ✅ BUILDING LEVEL
    long countBySocietyIdAndBuildingIdAndFlatStatus(
            Long societyId,
            Long buildingId,
            FlatStatus flatStatus
    );

    // ✅ FLOOR LEVEL
    long countBySocietyIdAndBuildingIdAndFloorIdAndFlatStatus(
            Long societyId,
            Long buildingId,
            Long floorId,
            FlatStatus status
    );

    Optional<Flat> findByIdAndSociety_Id(Long id, Long societyId);

    Optional<Flat> findByIdAndFloor_IdAndFloor_Building_IdAndFloor_Building_Society_Id(
            Long flatId,
            Long floorId,
            Long buildingId,
            Long societyId
    );


    boolean existsByIdAndSociety_Id(Long userId, Long societyId);

    List<Flat> findByFloor_Id(Long floorId);

    // COUNT FLATS IN FLOOR
    @Query("""
    SELECT f.flatStatus, COUNT(f)
    FROM Flat f
    WHERE f.floor.id = :floorId
    GROUP BY f.flatStatus
    """)
    List<Object[]> countByFloorStatus(Long floorId);


    // 🔹 BUILDING
    @Query("""
        SELECT f.flatStatus, COUNT(f)
        FROM Flat f
        WHERE f.building.id = :buildingId
        GROUP BY f.flatStatus
    """)
    List<Object[]> countByBuildingStatus(@Param("buildingId") Long buildingId);

    // 🔹 SOCIETY
    @Query("""
        SELECT f.flatStatus, COUNT(f)
        FROM Flat f
        WHERE f.society.id = :societyId
        GROUP BY f.flatStatus
    """)
    List<Object[]> countBySocietyStatus(@Param("societyId") Long societyId);


}
