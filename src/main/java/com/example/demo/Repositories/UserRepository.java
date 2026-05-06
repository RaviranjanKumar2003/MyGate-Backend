package com.example.demo.Repositories;

import com.example.demo.Entities.User;
import com.example.demo.Enums.NormalUserType;
import com.example.demo.Enums.UserRole;
import com.example.demo.Enums.UserStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ===== Society level =====

    // Simple fetch (NO pagination)
    List<User> findBySocietyId(Long societyId);

    // Pagination + sorting
    @Query("SELECT u FROM User u WHERE u.society.id = :societyId")
    List<User> findBySocietyIdWithPage(
            @Param("societyId") Long societyId,
            Pageable pageable
    );

    List<User> findBySocietyIdAndUserRole(Long societyId, UserRole role);

    List<User> findBySocietyIdAndUserStatus(Long societyId, UserStatus status);

    long countBySocietyId(Long societyId);

    long countBySocietyIdAndUserRole(Long societyId, UserRole role);

    long countBySocietyIdAndUserStatus(Long societyId, UserStatus status);

    // ===== Correct search query =====
    @Query("""
        SELECT u FROM User u
        WHERE u.society.id = :societyId
          AND u.userStatus = :status
          AND (
                LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
             OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
          )
    """)
    List<User> searchUsersInSociety(
            @Param("societyId") Long societyId,
            @Param("status") UserStatus status,
            @Param("keyword") String keyword
    );

    // Search by email only
    @Query("""
        SELECT u FROM User u
        WHERE u.society.id = :societyId
          AND LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))
    """)
    List<User> findBySocietyIdAndEmailContainingIgnoreCase(
            @Param("societyId") Long societyId,
            @Param("email") String email
    );


    // Super Admin ke liye
    List<User> findByUserRole(UserRole role);

    List<User> findByUserStatus(UserRole status);

    Optional<User> findByEmail(String email);



    Optional<User> findByEmailAndSocietyId(String email, Long societyId);

    boolean existsByEntryCode(String entryCode);

    Optional<User> findByIdAndSociety_Id(Long id, Long societyId);

    Optional<User> findByEntryCode(String entryCode);


    Optional<User> findByIdAndNormalUserType(Long id, NormalUserType type);




}
