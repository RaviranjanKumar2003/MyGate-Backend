package com.example.demo.Services;

import com.example.demo.Payloads.FlatOccupancyDto;

import java.util.List;

public interface FlatOccupancyService {

    FlatOccupancyDto createOccupancy(FlatOccupancyDto dto);

    List<FlatOccupancyDto> getByFlatId(Long flatId);

    FlatOccupancyDto endOccupancy(Long id);

    FlatOccupancyDto getCurrentOccupant(Long flatId);
}