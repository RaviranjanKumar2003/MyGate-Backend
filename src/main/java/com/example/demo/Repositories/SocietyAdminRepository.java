package com.example.demo.Repositories;

import com.example.demo.Entities.SocietyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocietyAdminRepository extends JpaRepository<SocietyAdmin, Long> {

    boolean existsByAdminEmail(String adminEmail);

    SocietyAdmin findFirstBySocietyId(Long societyId);

    Optional<SocietyAdmin> findByAdminEmail(String adminEmail);

    Optional<SocietyAdmin> findByIdAndSociety_Id(
            Long adminId,
            Long societyId
    );


}
