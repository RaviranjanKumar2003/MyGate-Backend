package com.example.demo.Repositories;

import com.example.demo.Entities.Flat;
import com.example.demo.Entities.FlatOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatOccupancyRepository extends JpaRepository<FlatOccupancy, Long> {

    // all history of a flat
    List<FlatOccupancy> findByFlatId(Long flatId);

    // current active occupant (important)
    FlatOccupancy findTopByFlatIdAndActiveTrue(Long flatId);

    List<FlatOccupancy> findByFlatOrderByStartDateDesc(Flat flat);
}