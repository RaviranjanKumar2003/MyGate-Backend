package com.example.demo.Services.Implementation;

import com.example.demo.Entities.*;
import com.example.demo.Enums.FlatStatus;
import com.example.demo.Enums.NormalUserType;
import com.example.demo.Enums.UserRole;
import com.example.demo.Enums.UserStatus;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Payloads.*;
import com.example.demo.Repositories.*;
import com.example.demo.Services.UserService;
import com.example.demo.Services.VerificationCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SocietyRepo societyRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private NormalUserRepository normalUserRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private FlatOwnershipRepository flatOwnershipRepository;

    @Autowired
    private FlatOccupancyRepository flatOccupancyRepository;



//=============================================================================================
private String generateUnique6DigitCode() {

    String code;

    do {
        // 6-digit random number (100000–999999)
        code = String.valueOf(
                ThreadLocalRandom.current().nextInt(100000, 1000000)
        );
    } while (userRepository.existsByEntryCode(code));

    return code;
}


// ==================================================== CREATE USER

@Override
@Transactional
public UserDto createUser(UserDto userDto) throws Exception {

    if (userDto.getSocietyId() == null) {
        throw new BadRequestException("Society ID is required");
    }

    Society society = societyRepo.findById(userDto.getSocietyId())
            .orElseThrow(() -> new ResourceNotFoundException("Society", "id", userDto.getSocietyId()));

    Optional<User> existingUserOpt =
            userRepository.findByEmailAndSocietyId(userDto.getEmail(), userDto.getSocietyId());

    User user;

    // ================= USER CREATE / UPDATE =================
    if (existingUserOpt.isPresent()) {

        user = existingUserOpt.get();

        user.setName(Optional.ofNullable(userDto.getName()).orElse(user.getName()));
        user.setMobileNumber(Optional.ofNullable(userDto.getMobileNumber()).orElse(user.getMobileNumber()));
        user.setUserRole(Optional.ofNullable(userDto.getUserRole()).orElse(user.getUserRole()));
        user.setUserStatus(Optional.ofNullable(userDto.getUserStatus()).orElse(user.getUserStatus()));

        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

    } else {

        user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setMobileNumber(userDto.getMobileNumber());
        user.setUserRole(userDto.getUserRole());
        user.setUserStatus(userDto.getUserStatus());
        user.setSociety(society);

        String entryCode = generateUnique6DigitCode();
        user.setEntryCode(entryCode);

        user = userRepository.save(user);

        String qrText = "USER:" + user.getId()
                + "|CODE:" + entryCode
                + "|ROLE:" + user.getUserRole()
                + "|SOCIETY:" + society.getId();

        String qrPath = generateQrCode(qrText, user.getId());
        user.setQrCodePath(qrPath);

        user = userRepository.save(user);
    }

    // ================= NORMAL USER =================
    if (user.getUserRole() == UserRole.NORMAL_USER) {

        if (userDto.getFlatId() == null || userDto.getNormalUserType() == null) {
            throw new BadRequestException("Flat & NormalUserType required");
        }

        Boolean isLiving = userDto.getIsLiving() != null ? userDto.getIsLiving() : true;

        Flat flat = flatRepository.findById(userDto.getFlatId())
                .orElseThrow(() -> new ResourceNotFoundException("Flat", "id", userDto.getFlatId()));

        // 🔥 EXISTING OWNER
        List<FlatOwnership> existingOwners =
                flatOwnershipRepository.findByFlatAndTypeAndActiveTrue(flat, NormalUserType.OWNER);

        FlatOwnership existingOwnership =
                existingOwners.isEmpty() ? null : existingOwners.get(0);

        // 🔥 CURRENT OCCUPANT
        FlatOccupancy currentOccupant =
                flatOccupancyRepository.findTopByFlatIdAndActiveTrue(flat.getId());

        // ================= RULES =================

        // ❌ OWNER already exists
        if (userDto.getNormalUserType() == NormalUserType.OWNER && existingOwnership != null) {
            throw new BadRequestException("Owner already exists for this flat");
        }

        // ❌ OWNER living → tenant blocked
        if (userDto.getNormalUserType() == NormalUserType.TENANT &&
                currentOccupant != null &&
                currentOccupant.getType() == NormalUserType.OWNER &&
                Boolean.TRUE.equals(currentOccupant.getIsLiving())) {

            throw new BadRequestException("Owner is currently living. Tenant not allowed");
        }

        // ================= NORMAL USER SAVE (FIXED) =================
        NormalUser normalUser = normalUserRepository
                .findByUser(user)
                .orElse(null);

        if (normalUser == null) {
            normalUser = new NormalUser();
            normalUser.setUser(user);   // only for new
        }

        // update fields
        normalUser.setEmail(user.getEmail());

        if (userDto.getPassword() != null) {
            normalUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        normalUser.setBuilding(buildingRepo.findById(userDto.getBuildingId())
                .orElseThrow(() -> new ResourceNotFoundException("Building", "id", userDto.getBuildingId())));

        normalUser.setFloor(floorRepository.findById(userDto.getFloorId())
                .orElseThrow(() -> new ResourceNotFoundException("Floor", "id", userDto.getFloorId())));

        normalUser.setFlat(flat);
        normalUser.setNormalUserType(userDto.getNormalUserType());

        normalUserRepository.save(normalUser);

        // ================= OWNERSHIP =================
        if (userDto.getNormalUserType() == NormalUserType.OWNER) {

            FlatOwnership ownership = new FlatOwnership();
            ownership.setFlat(flat);
            ownership.setUser(user);
            ownership.setType(NormalUserType.OWNER);
            ownership.setStartDate(LocalDate.now());
            ownership.setActive(true);

            flatOwnershipRepository.save(ownership);
        }

        // ================= OCCUPANCY =================
        if (isLiving) {

            if (currentOccupant != null) {
                currentOccupant.setActive(false);
                currentOccupant.setIsLiving(false);
                currentOccupant.setEndDate(LocalDate.now());
                flatOccupancyRepository.save(currentOccupant);
            }

            FlatOccupancy newOccupancy = new FlatOccupancy();
            newOccupancy.setFlat(flat);
            newOccupancy.setUser(user);
            newOccupancy.setType(userDto.getNormalUserType());
            newOccupancy.setStartDate(LocalDate.now());
            newOccupancy.setActive(true);
            newOccupancy.setIsLiving(true);

            flatOccupancyRepository.save(newOccupancy);

            if (userDto.getNormalUserType() == NormalUserType.OWNER) {
                flat.setFlatStatus(FlatStatus.OWNER_OCCUPIED);
            } else {
                flat.setFlatStatus(FlatStatus.TENANT_OCCUPIED);
            }

        } else {
            flat.setFlatStatus(FlatStatus.VACANT);
        }

        flatRepository.save(flat);
    }

    // ================= STAFF =================
    if (user.getUserRole() == UserRole.STAFF) {

        if (userDto.getStaffType() == null || userDto.getStaffLevel() == null) {
            throw new BadRequestException("StaffType & StaffLevel required");
        }

        Staff staff = staffRepository.findByUser(user).orElse(new Staff());

        staff.setUser(user);
        staff.setStaffType(userDto.getStaffType());
        staff.setStaffLevel(userDto.getStaffLevel());
        staff.setDutyTiming(userDto.getDutyTiming());
        staff.setSalary(userDto.getSalary());
        staff.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null) {
            staff.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        staffRepository.save(staff);
    }

    // ================= RESPONSE =================
    UserDto response = new UserDto();

    response.setId(user.getId());
    response.setName(user.getName());
    response.setEmail(user.getEmail());
    response.setMobileNumber(user.getMobileNumber());
    response.setUserRole(user.getUserRole());
    response.setUserStatus(user.getUserStatus());
    response.setSocietyId(user.getSociety().getId());
    response.setCreatedAt(user.getCreatedAt());
    response.setEntryCode(user.getEntryCode());
    response.setQrCodePath(user.getQrCodePath());

    return response;
}


//========================================================================================= GENERATE QR
private String generateQrCode(String text, Long  userId) throws Exception {

    int width = 300;
    int height = 300;

    BitMatrix matrix = new MultiFormatWriter()
            .encode(text, BarcodeFormat.QR_CODE, width, height);

    // 🔥 DIRECTORY PATH
    String dirPath = "uploads/qr";
    File dir = new File(dirPath);

    // 🔥 CREATE FOLDER IF NOT EXISTS
    if (!dir.exists()) {
        dir.mkdirs();
    }

    // 🔥 FILE PATH
    String filePath = dirPath + "/user_" + userId + ".png";
    Path path = Paths.get(filePath);

    MatrixToImageWriter.writeToPath(matrix, "PNG", path);

    return filePath;
}




    //==================================================================================================
// GET ALL USERS (STAFF + NORMAL USER) IN A SOCIETY
   @Override
   public List<UserProfileDto> getAllUsersOfSociety(Long societyId) {

       List<User> users = userRepository.findBySocietyId(societyId);

       return users.stream().map(user -> {

           UserProfileDto dto = new UserProfileDto();
           dto.setId(user.getId());
           dto.setName(user.getName());
           dto.setEmail(user.getEmail());
           dto.setMobileNumber(user.getMobileNumber());
           dto.setUserRole(user.getUserRole());
           dto.setUserStatus(user.getUserStatus());
           dto.setCreatedAt(user.getCreatedAt());
           dto.setPassword(null); // hide password

           //  NORMAL USER
           if (user.getUserRole() == UserRole.NORMAL_USER) {
                  normalUserRepository.findByUser(user).ifPresent(nu -> {
                   dto.setNormalUserType(nu.getNormalUserType());
                   dto.setBuildingId(nu.getBuilding().getId());
                   dto.setFloorId(nu.getFloor().getId());
                   dto.setFlatId(nu.getFlat().getId());
               });
           }

           //  STAFF
           if (user.getUserRole() == UserRole.STAFF) {
               staffRepository.findByUser(user).ifPresent(staff -> {
                   dto.setStaffType(staff.getStaffType());
                   dto.setStaffLevel(staff.getStaffLevel());
                   dto.setDutyTiming(staff.getDutyTiming());
                   dto.setSalary(staff.getSalary());
               });
           }

           return dto;

       }).collect(Collectors.toList());
   }



// GET SOCIETY SUMMARY
    @Override
    public UserCountResponse getSocietyUserSummary(Long societyId) {
        UserCountResponse response = new UserCountResponse();

        response.setTotalUsers(userRepository.countBySocietyId(societyId));

        Map<String, Long> roleWise = new HashMap<>();
        for (UserRole role : UserRole.values()) {
            roleWise.put(role.name(), userRepository.countBySocietyIdAndUserRole(societyId, role));
        }

        Map<String, Long> statusWise = new HashMap<>();
        for (UserStatus status : UserStatus.values()) {
            statusWise.put(status.name(), userRepository.countBySocietyIdAndUserStatus(societyId, status));
        }

        response.setRoleWiseCount(roleWise);
        response.setStatusWiseCount(statusWise);

        return response;
    }


// GET USER IN A SOCIETY BY ROLE
    @Override
    public List<UserDto> getUsersOfSocietyByRole(Long societyId, UserRole role) {
        return userRepository.findBySocietyIdAndUserRole(societyId, role)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


// GET USER BY STATUS
    @Override
    public List<UserDto> getUsersOfSocietyByStatus(Long societyId, UserStatus status) {
        return userRepository.findBySocietyIdAndUserStatus(societyId, status)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


// GET USER BY ID
@Override
public UserDto getUserById(Long userId, Long societyId) {

    User user = userRepository
            .findByIdAndSociety_Id(userId, societyId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User", "id", userId)
            );

    // ✅ USE EXISTING COMPLETE MAPPER
    UserDto dto = mapToDto(user);

    // ✅ ENTRY QR EXTRA
    dto.setEntryCode(user.getEntryCode());
    dto.setQrCodePath(user.getQrCodePath());

    return dto;
}




    // ==================== GET BY Flat ID ====================
    @Override
    public NormalUserProfileDto getUserByFlatId(Long flatId) {

        NormalUser normalUser = normalUserRepository.findByFlatId(flatId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "NormalUser", "flatId", flatId));

        User user = normalUser.getUser();

        NormalUserProfileDto dto = new NormalUserProfileDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserStatus(user.getUserStatus());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setNormalUserType(normalUser.getNormalUserType());
        dto.setImageUrl(user.getImageURL());

        return dto;
    }




// UPDATE

    @Override
    public UserDto updateUser(Long userId, UserDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getMobileNumber() != null) user.setMobileNumber(dto.getMobileNumber());
        if (dto.getUserStatus() != null) user.setUserStatus(dto.getUserStatus());
        if (dto.getUserRole() != null) user.setUserRole(dto.getUserRole());
        if (dto.getImageURL() != null) user.setImageURL(dto.getImageURL());

        return mapToDto(userRepository.save(user));
    }



// DELETE USERS
@Override
@Transactional
public ApiResponse deleteUser(Long userId, Long societyId, Long buildingId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getSociety().getId().equals(societyId)) {
        throw new RuntimeException("User does not belong to this society");
    }

    // ================= NORMAL USER =================
    if (user.getUserRole() == UserRole.NORMAL_USER) {

        normalUserRepository.findByUser(user).ifPresent(normalUser -> {

            Flat flat = normalUser.getFlat();

            if (flat != null) {

                List<FlatOwnership> ownershipList =
                        flatOwnershipRepository.findByFlatAndActiveTrue(flat);

                ownershipList.forEach(ownership -> {
                    ownership.setActive(false);
                    ownership.setEndDate(LocalDate.now());
                });

                flatOwnershipRepository.saveAll(ownershipList);

                // 🔥 reset flat
                flat.setFlatStatus(FlatStatus.VACANT);
                flatRepository.save(flat);
            }

            normalUserRepository.delete(normalUser);
        });
    }

    // ================= STAFF =================
    if (user.getUserRole() == UserRole.STAFF) {
        staffRepository.findByUser(user)
                .ifPresent(staffRepository::delete);
    }

    // ================= USER DELETE =================
    user.setUserStatus(UserStatus.INACTIVE);
    userRepository.save(user);

    return new ApiResponse(
            "User deleted successfully, history preserved",
            true
    );
}




/* ========================================== PAGINATION & SORT ================================*/
    @Override
    public List<UserDto> getUsersOfSocietyPaged(Long societyId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return userRepository.findBySocietyIdWithPage(societyId, PageRequest.of(page, size, sort))
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ==================== SEARCH ====================
    @Override
    public List<UserDto> searchUsersByEmailInSociety(Long societyId, String emailKeyword) {
        return userRepository.findBySocietyIdAndEmailContainingIgnoreCase(societyId, emailKeyword)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> searchUserByName(Long societyId, String keyword) {
        return userRepository.searchUsersInSociety(societyId, UserStatus.ACTIVE, keyword)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

/* ====================================== IMAGE ======================================*/
    @Override
    public UserDto updateUserImage(Long userId, MultipartFile image) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // MultipartFile -> String (path / URL)
        String imageUrl = saveFile(image);

        // ✅ CORRECT setter
        user.setImageURL(imageUrl);

        userRepository.save(user);
        return mapToDto(user);
    }

    private String saveFile(MultipartFile file) {

        try {
            String uploadDir = "uploads/profile-images/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            Files.write(filePath, file.getBytes());

            // ✅ ONLY FILE NAME SAVE
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Image save failed");
        }
    }




/*===================================== HELPER METHOD ==================================================*/
    private UserDto mapToDto(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setImageURL(user.getImageURL());

        // ✅ SOCIETY
        if (user.getSociety() != null) {
            dto.setSocietyId(user.getSociety().getId());
            dto.setSocietyName(user.getSociety().getName());
        }

        dto.setUserRole(user.getUserRole());
        dto.setUserStatus(user.getUserStatus());

        // ================= NORMAL USER =================
        if (user.getUserRole() == UserRole.NORMAL_USER) {

            NormalUser normalUser = normalUserRepository.findByUser(user)
                    .orElse(null);

            if (normalUser != null) {

                dto.setNormalUserType(normalUser.getNormalUserType());

                // BUILDING
                if (normalUser.getBuilding() != null) {
                    dto.setBuildingId(normalUser.getBuilding().getId());
                    dto.setBuildingName(normalUser.getBuilding().getName());
                }

                // FLOOR
                if (normalUser.getFloor() != null) {
                    dto.setFloorId(normalUser.getFloor().getId());
                    dto.setFloorNumber(normalUser.getFloor().getFloorNumber());
                }

                // FLAT
                if (normalUser.getFlat() != null) {
                    dto.setFlatId(normalUser.getFlat().getId());
                    dto.setFlatNumber(normalUser.getFlat().getFlatNumber());
                }
            }
        }

        // ================= STAFF =================
        if (user.getUserRole() == UserRole.STAFF) {

            Staff staff = staffRepository.findByUser(user).orElse(null);

            if (staff != null) {
                dto.setStaffType(staff.getStaffType());
                dto.setStaffLevel(staff.getStaffLevel());
                dto.setDutyTiming(staff.getDutyTiming());
                dto.setSalary(staff.getSalary());
            }
        }

        return dto;
    }



    private User mapToEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setMobileNumber(dto.getMobileNumber());
        user.setUserStatus(dto.getUserStatus());
        user.setUserRole(dto.getUserRole());
        user.setImageURL(dto.getImageURL());
        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
