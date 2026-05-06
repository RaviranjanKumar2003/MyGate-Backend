package com.example.demo.Repositories;

import com.example.demo.Entities.FlatListing;
import com.example.demo.Enums.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatListingRepository extends JpaRepository<FlatListing, Long> {

    List<FlatListing> findBySocietyId(Long societyId);

    List<FlatListing> findByOwnerId(Long ownerId);

    List<FlatListing> findBySocietyIdAndStatus(
            Long societyId,
            ListingStatus status
    );
}