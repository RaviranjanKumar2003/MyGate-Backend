package com.example.demo.Services;

import com.example.demo.Entities.FlatListing;
import com.example.demo.Payloads.FlatListingDto;
import com.example.demo.Payloads.FlatListingResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlatListingService {

    FlatListing createListing(FlatListingDto dto);

    List<FlatListingResponseDto> getAllBySociety(Long societyId);

    List<FlatListing> getByOwner(Long ownerId);

    FlatListing updateStatus(Long id, String status);

    List<FlatListing> getPendingBySociety(Long societyId);

    void uploadImages(Long id, List<MultipartFile> files);

    FlatListing updateFlat(Long id, FlatListingDto dto);

    void deleteFlat(Long id);

    // ✅ IMAGE DELETE (🔥 NEW ADD)
    void deleteImage(Long id, String imageName);
}