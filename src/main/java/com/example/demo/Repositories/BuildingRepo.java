package com.example.demo.Repositories;

import com.example.demo.Entities.Building;
import com.example.demo.Enums.BuildingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuildingRepo extends JpaRepository<Building,Long> {

    List<Building> findBySociety_IdAndIsActive(Long societyId,BuildingStatus status);

    Optional<Building> findByIdAndIsActive(Long id,BuildingStatus status);

    List<Building> findByNameContainingIgnoreCaseAndIsActive(String name,BuildingStatus status);

    Optional<Building> findByIdAndSociety_Id(Long id, Long societyId);

    List<Building> findBySocietyId(Long societyId);

}
