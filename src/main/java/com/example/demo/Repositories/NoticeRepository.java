package com.example.demo.Repositories;

import com.example.demo.Entities.Notice;
import com.example.demo.Enums.NoticeCreatedByRole;
import com.example.demo.Enums.TargetAudience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByTargetSocietyIdAndTargetRoleInAndIsActiveTrue( Long societyId, List<TargetAudience> roles );

    List<Notice> findByTargetSocietyIdIsNullAndTargetRoleAndIsActiveTrue( TargetAudience role);


    List<Notice> findByCreatedByIdAndCreatedByRole(Long createdById, NoticeCreatedByRole createdByRole);

    List<Notice> findByTargetSocietyId(Long societyId);

    @Query("SELECT n FROM Notice n " +
            "WHERE n.isActive = true " +
            "AND (n.targetSocietyId IS NULL OR n.targetSocietyId = :societyId) " +
            "ORDER BY n.createdAt DESC")
    List<Notice> findNoticesForSociety(@Param("societyId") Long societyId);


    @Query("SELECT n FROM Notice n " +
            "WHERE n.isActive = true " +
            "AND (n.targetRole = :role OR n.targetRole = com.example.demo.Enums.TargetAudience.ALL) " +
            "AND (n.targetSocietyId IS NULL OR n.targetSocietyId = :societyId) " +
            "ORDER BY n.createdAt DESC")
    List<Notice> findNoticesForStaff(
            @Param("societyId") Long societyId,
            @Param("role") TargetAudience role
    );



    @Query("""
        SELECT n FROM Notice n
        WHERE n.isActive = true
        AND (
             n.targetSocietyId = :societyId
             OR n.targetSocietyId IS NULL
        )
        AND n.createdByRole IN (
             com.example.demo.Enums.NoticeCreatedByRole.SUPER_ADMIN,
             com.example.demo.Enums.NoticeCreatedByRole.SOCIETY_ADMIN
        )
        ORDER BY n.createdAt DESC
    """)
    List<Notice> findNoticesForNormalUser(@Param("societyId") Long societyId);





    // Notices for STAFF in a society or global notices
    List<Notice> findByTargetSocietyIdAndTargetRoleOrTargetRoleIsNull(
            Long societyId,
            TargetAudience targetRole
    );

    @Query("""
SELECT n FROM Notice n
WHERE n.isActive = true
AND (n.targetSocietyId IS NULL OR n.targetSocietyId = :societyId)
""")
    List<Notice> findActiveNotices(@Param("societyId") Long societyId);



    @Query("""
SELECT n FROM Notice n
WHERE n.isActive = true
AND (
    n.targetSocietyId IS NULL OR n.targetSocietyId = :societyId
)
AND (
    n.targetRole = :role OR n.targetRole = com.example.demo.Enums.TargetAudience.ALL
)
AND n.createdAt <= :userCreatedAt
ORDER BY n.createdAt DESC
""")
    List<Notice> findNoticesForStaffWithDateFilter(
            @Param("societyId") Long societyId,
            @Param("role") TargetAudience role,
            @Param("userCreatedAt") java.time.LocalDateTime userCreatedAt
    );

    @Query("""
SELECT n FROM Notice n
WHERE n.isActive = true
AND (n.targetSocietyId IS NULL OR n.targetSocietyId = :societyId)
AND n.createdAt <= :userCreatedAt
ORDER BY n.createdAt DESC
""")
    List<Notice> findNoticesForSocietyWithDateFilter(
            @Param("societyId") Long societyId,
            @Param("userCreatedAt") java.time.LocalDateTime userCreatedAt
    );


    @Query("""
SELECT n FROM Notice n
WHERE n.isActive = true
AND n.targetSocietyId IS NULL
AND n.targetRole = :role
AND n.createdAt <= :userCreatedAt
ORDER BY n.createdAt DESC
""")
    List<Notice> findGlobalNoticesWithDateFilter(
            @Param("role") TargetAudience role,
            @Param("userCreatedAt") LocalDateTime userCreatedAt
    );


    @Query("""
SELECT n FROM Notice n
WHERE n.isActive = true
AND (
    n.targetSocietyId = :societyId
    OR n.targetSocietyId IS NULL
)
AND n.createdAt >= :userCreatedAt
ORDER BY n.createdAt DESC
""")
    List<Notice> findNoticesForNormalUserWithDateFilter(
            @Param("societyId") Long societyId,
            @Param("userCreatedAt") LocalDateTime userCreatedAt
    );







}
