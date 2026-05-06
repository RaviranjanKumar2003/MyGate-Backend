package com.example.demo.Repositories;

import com.example.demo.Entities.*;
import com.example.demo.Enums.NormalUserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlatOwnershipRepository extends JpaRepository<FlatOwnership, Long> {

    List<FlatOwnership> findByFlatOrderByStartDateDesc(Flat flat);

    List<FlatOwnership> findByFlatAndTypeAndActiveTrue(
            Flat flat,
            NormalUserType type
    );

    List<FlatOwnership> findByFlatAndActiveTrue(Flat flat);


}