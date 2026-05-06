package com.example.demo.Services;

import com.example.demo.Payloads.FlatHistoryDto;
import com.example.demo.Enums.NormalUserType;

import java.util.List;

public interface FlatOwnershipService {

    void assignOwnership(Long flatId, Long userId, NormalUserType type, boolean isLiving);

    void updateFlatStatus(Long flatId);

    List<FlatHistoryDto> getFlatHistory(Long flatId);

}