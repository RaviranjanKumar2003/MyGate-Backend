package com.example.demo.Services;

import com.example.demo.Entities.User;
import com.example.demo.Enums.UserRole;
import com.example.demo.Enums.UserStatus;
import com.example.demo.Payloads.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

// CREATE
    UserDto createUser(UserDto userDto) throws Exception;

//==============================  GET USERS ===========================================

    //=========== SOCIETY LEVEL
    // 1.
    List<UserProfileDto> getAllUsersOfSociety(Long societyId);
    // 2.
    UserCountResponse getSocietyUserSummary(Long societyId);
    // 3.
    List<UserDto> getUsersOfSocietyByRole(Long societyId, UserRole role);
    // 4.
    List<UserDto> getUsersOfSocietyByStatus(Long societyId, UserStatus status);


    UserDto getUserById(Long userId, Long societyId);



// UPDATE & DELETE
    UserDto updateUser(Long userId, UserDto dto);



// DELETE USER
    ApiResponse deleteUser(Long userId, Long societyId, Long buildingId);



    // ==================== PAGINATION & SORT ====================
    List<UserDto> getUsersOfSocietyPaged(Long societyId, int page, int size, String sortBy, String sortDir);

    // Search users by email in a society
    List<UserDto> searchUsersByEmailInSociety(Long societyId, String emailKeyword);


// SEARCH USER BY NAME
    List<UserDto> searchUserByName(Long societyId, String keyword);


    NormalUserProfileDto getUserByFlatId(Long flatId);


    // ==================== IMAGE ====================
    UserDto updateUserImage(Long userId, MultipartFile image);

    User findById(Long id);


}
