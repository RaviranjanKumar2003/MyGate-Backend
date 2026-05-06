package com.example.demo.Repositories;

import com.example.demo.Entities.NormalUser;
import com.example.demo.Entities.Staff;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NormalUserRepository extends JpaRepository<NormalUser, Long> {

    Optional<NormalUser> findByUser(User user);

    void deleteByUser(User user);

    Optional<NormalUser> findByFlatId(Long flatId);



    Optional<NormalUser> findByFlat_Floor_Building_Society_IdAndBuilding_IdAndFloor_IdAndFlat_Id(
            Long societyId, Long buildingId, Long floorId, Long flatId
    );

    Optional<NormalUser> findByUserId(Long userId);



}
