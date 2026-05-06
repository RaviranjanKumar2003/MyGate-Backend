package com.example.demo.Services;

import com.example.demo.Payloads.BuildingFullDto;
import com.example.demo.Payloads.SocietyDto;

import java.util.List;

public interface SocietyService {

// CREATE
    SocietyDto createSociety(SocietyDto societyDto);

// GET ALL Active Society
    List<SocietyDto> getAllActiveSocieties();

// GET ALL De-Active Society
    List<SocietyDto> getAllDeActiveSocieties();

// GET BY ID
    SocietyDto getSocietyById(Long id);

    // UPDATE
    SocietyDto updateSociety(SocietyDto societyDto, Long id);

    // DELETE
    void deleteSociety(Long id);

    // SEARCH
    List<SocietyDto> searchSociety(String keyword);


    public List<BuildingFullDto> getFullBuildings(Long societyId);






}
