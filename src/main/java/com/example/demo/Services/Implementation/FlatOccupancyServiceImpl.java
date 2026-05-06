package com.example.demo.Services.Implementation;

import com.example.demo.Entities.Flat;
import com.example.demo.Entities.FlatOccupancy;
import com.example.demo.Entities.User;
import com.example.demo.Payloads.FlatOccupancyDto;
import com.example.demo.Repositories.FlatOccupancyRepository;
import com.example.demo.Repositories.FlatRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.FlatOccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Enums.FlatStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatOccupancyServiceImpl implements FlatOccupancyService {

    @Autowired
    private FlatOccupancyRepository repo;

    @Autowired
    private FlatRepository flatRepo;

    @Autowired
    private UserRepository userRepo;

    // ================= CREATE OCCUPANCY =================
    @Override
    public FlatOccupancyDto createOccupancy(FlatOccupancyDto dto) {

        Flat flat = flatRepo.findById(dto.getFlatId())
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // STEP 1: old occupant deactivate karo
        FlatOccupancy existing = repo.findTopByFlatIdAndActiveTrue(dto.getFlatId());

        if (existing != null) {
            existing.setActive(false);
            existing.setIsLiving(false);
            existing.setEndDate(LocalDate.now());
            repo.save(existing);
        }

        //  STEP 2: new occupancy create karo
        FlatOccupancy o = new FlatOccupancy();
        o.setFlat(flat);
        o.setUser(user);
        o.setType(dto.getType()); // OWNER / TENANT
        o.setStartDate(LocalDate.now());
        o.setActive(true);
        o.setIsLiving(true);

        repo.save(o);

        //  STEP 3: flat status update karo
        if (dto.getType().name().equals("OWNER")) {
            flat.setFlatStatus(FlatStatus.OWNER_OCCUPIED);
        } else {
            flat.setFlatStatus(FlatStatus.TENANT_OCCUPIED);
        }

        flatRepo.save(flat);

        //  RESPONSE
        dto.setId(o.getId());
        dto.setStartDate(o.getStartDate());
        dto.setActive(true);

        return dto;
    }

    // ================= GET ALL HISTORY =================
    @Override
    public List<FlatOccupancyDto> getByFlatId(Long flatId) {

        return repo.findByFlatId(flatId)
                .stream()
                .map(o -> {
                    FlatOccupancyDto dto = new FlatOccupancyDto();
                    dto.setId(o.getId());
                    dto.setFlatId(o.getFlat().getId());
                    dto.setUserId(o.getUser().getId());
                    dto.setType(o.getType());
                    dto.setStartDate(o.getStartDate());
                    dto.setEndDate(o.getEndDate());
                    dto.setActive(o.getActive());
                    dto.setIsLiving(o.getIsLiving());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ================= END OCCUPANCY =================
    @Override
    public FlatOccupancyDto endOccupancy(Long id) {

        FlatOccupancy o = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Occupancy not found"));

        o.setActive(false);
        o.setIsLiving(false);
        o.setEndDate(LocalDate.now());

        repo.save(o);

        //  flat ko VACANT karo
        Flat flat = o.getFlat();
        flat.setFlatStatus(FlatStatus.VACANT);
        flatRepo.save(flat);

        FlatOccupancyDto dto = new FlatOccupancyDto();
        dto.setId(o.getId());

        return dto;
    }

    // ================= CURRENT OCCUPANT =================
    @Override
    public FlatOccupancyDto getCurrentOccupant(Long flatId) {

        FlatOccupancy o = repo.findTopByFlatIdAndActiveTrue(flatId);

        if (o == null) return null;

        FlatOccupancyDto dto = new FlatOccupancyDto();
        dto.setId(o.getId());
        dto.setUserId(o.getUser().getId());
        dto.setType(o.getType());
        dto.setIsLiving(o.getIsLiving());

        return dto;
    }
}