package com.example.demo.Services.Implementation;

import com.example.demo.Entities.*;
import com.example.demo.Enums.*;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repositories.*;
import com.example.demo.Services.FlatOwnershipService;
import com.example.demo.Payloads.FlatHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlatOwnershipServiceImpl implements FlatOwnershipService {

    @Autowired
    private FlatOwnershipRepository flatOwnershipRepository;

    @Autowired
    private FlatOccupancyRepository flatOccupancyRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= ASSIGN OWNERSHIP =================
    @Override
    public void assignOwnership(Long flatId, Long userId, NormalUserType type, boolean isLiving) {

        Flat flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new ResourceNotFoundException("Flat", "id", flatId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // 🔥 ONLY SAME TYPE CLOSE KARO (OWNER OR TENANT)
        List<FlatOwnership> oldList =
                flatOwnershipRepository.findByFlatAndTypeAndActiveTrue(flat, type);

        for (FlatOwnership o : oldList) {
            o.setActive(false);
            o.setEndDate(LocalDate.now());
        }

        flatOwnershipRepository.saveAll(oldList);

        // NEW ENTRY
        FlatOwnership ownership = new FlatOwnership();
        ownership.setFlat(flat);
        ownership.setUser(user);
        ownership.setType(type);
        ownership.setStartDate(LocalDate.now());
        ownership.setActive(true);
        ownership.setLiving(isLiving);

        flatOwnershipRepository.save(ownership);

        updateFlatStatus(flatId);
    }

    // ================= UPDATE FLAT STATUS =================
    @Override
    public void updateFlatStatus(Long flatId) {

        Flat flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new ResourceNotFoundException("Flat", "id", flatId));

        List<FlatOwnership> owners =
                flatOwnershipRepository.findByFlatAndTypeAndActiveTrue(flat, NormalUserType.OWNER);

        List<FlatOwnership> tenants =
                flatOwnershipRepository.findByFlatAndTypeAndActiveTrue(flat, NormalUserType.TENANT);

        // 🔥 PRIORITY: TENANT > OWNER > VACANT
        if (!tenants.isEmpty() && Boolean.TRUE.equals(tenants.get(0).getLiving())) {
            flat.setFlatStatus(FlatStatus.TENANT_OCCUPIED);
        }
        else if (!owners.isEmpty() && Boolean.TRUE.equals(owners.get(0).getLiving())) {
            flat.setFlatStatus(FlatStatus.OWNER_OCCUPIED);
        }
        else {
            flat.setFlatStatus(FlatStatus.VACANT);
        }

        flatRepository.save(flat);
    }

    // ================= HISTORY =================
    @Override
    public List<FlatHistoryDto> getFlatHistory(Long flatId) {

        Flat flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new ResourceNotFoundException("Flat", "id", flatId));

        // 1️⃣ OWNERSHIP HISTORY
        List<FlatOwnership> ownerships =
                flatOwnershipRepository.findByFlatOrderByStartDateDesc(flat);

        List<FlatOccupancy> occupancies =
                flatOccupancyRepository.findByFlatOrderByStartDateDesc(flat);

        List<FlatHistoryDto> result = new ArrayList<>();

        // ================= OWNER HISTORY =================
        for (FlatOwnership o : ownerships) {

            FlatHistoryDto dto = new FlatHistoryDto();

            dto.setId(o.getId());
            dto.setUserId(o.getUser().getId());
            dto.setUserName(o.getUser().getName());
            dto.setEmail(o.getUser().getEmail());

            dto.setType("OWNER");

            dto.setStartDate(o.getStartDate());
            dto.setEndDate(o.getEndDate());

            dto.setActive(o.getActive());
            dto.setLiving(o.getLiving());

            result.add(dto);
        }

        // ================= TENANT HISTORY =================
        for (FlatOccupancy o : occupancies) {

            FlatHistoryDto dto = new FlatHistoryDto();

            dto.setId(o.getId());
            dto.setUserId(o.getUser().getId());
            dto.setUserName(o.getUser().getName());
            dto.setEmail(o.getUser().getEmail());

            dto.setType(o.getType().name()); // TENANT / OWNER (occupancy type)

            dto.setStartDate(o.getStartDate());
            dto.setEndDate(o.getEndDate());

            dto.setActive(o.getActive());
            dto.setLiving(o.getIsLiving());

            result.add(dto);
        }

        return result;
    }
}