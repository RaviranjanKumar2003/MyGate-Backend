package com.example.demo.Services.Implementation;

import com.example.demo.Entities.FlatListing;
import com.example.demo.Entities.NormalUser;
import com.example.demo.Entities.User;
import com.example.demo.Enums.ListingStatus;
import com.example.demo.Enums.ListingType;
import com.example.demo.Enums.NormalUserType;
import com.example.demo.Payloads.FlatListingDto;
import com.example.demo.Payloads.FlatListingResponseDto;
import com.example.demo.Repositories.FlatListingRepository;
import com.example.demo.Repositories.NormalUserRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.FlatListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlatListingServiceImpl implements FlatListingService {

    private final FlatListingRepository repo;
    private final UserRepository userRepo;

    @Autowired
    private NormalUserRepository normalUserRepository;

    public FlatListingServiceImpl(FlatListingRepository repo,
                                  UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public FlatListing createListing(FlatListingDto dto) {

        NormalUser normalUser = normalUserRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (normalUser.getNormalUserType() != NormalUserType.OWNER) {
            throw new RuntimeException("Only OWNER can publish flat");
        }

        FlatListing listing = new FlatListing();

        listing.setFlatId(dto.getFlatId());
        listing.setOwnerId(dto.getOwnerId());
        listing.setSocietyId(dto.getSocietyId());
        listing.setDescription(dto.getDescription());

        ListingType type = ListingType.valueOf(dto.getType().toUpperCase());
        listing.setType(type);

        if (type == ListingType.SELL) {
            listing.setPrice(dto.getPrice());
            listing.setRent(null);
        } else {
            listing.setRent(dto.getRent());
            listing.setPrice(null);
        }

        // ✅ DIRECT LIVE (NO ADMIN APPROVAL)
        listing.setStatus(ListingStatus.ACTIVE);

        return repo.save(listing);
    }

    @Override
    public List<FlatListing> getByOwner(Long ownerId) {
        return repo.findByOwnerId(ownerId);
    }

    @Override
    public FlatListing updateStatus(Long id, String status) {

        FlatListing listing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        try {
            listing.setStatus(ListingStatus.valueOf(status.toUpperCase()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid status");
        }

        return repo.save(listing);
    }

    @Override
    public List<FlatListing> getPendingBySociety(Long societyId) {
        return repo.findBySocietyIdAndStatus(
                societyId,
                ListingStatus.PENDING
        );
    }

    public List<FlatListingResponseDto> getAllBySociety(Long societyId) {

        List<FlatListing> listings = repo.findBySocietyId(societyId);

        return listings.stream().map(l -> {

            User user = userRepo.findById(l.getOwnerId()).orElse(null);

            FlatListingResponseDto dto = new FlatListingResponseDto();

            dto.setId(l.getId());
            dto.setFlatId(l.getFlatId());
            dto.setOwnerId(l.getOwnerId());

            dto.setType(l.getType().name());
            dto.setPrice(l.getPrice());
            dto.setRent(l.getRent());
            dto.setStatus(l.getStatus().name());
            dto.setDescription(l.getDescription());

            // ✅ OWNER DETAILS
            if (user != null) {
                dto.setOwnerName(user.getName());
                dto.setOwnerMobile(user.getMobileNumber());
                dto.setOwnerEmail(user.getEmail()); // 🔥 ADD THIS
            }

            // ✅ FLAT NUMBER (better source)
            if (user != null && user.getFlat() != null) {
                dto.setFlatNumber(user.getFlat().getFlatNumber());
            }

            // 🔥 MOST IMPORTANT (IMAGES)
            dto.setImages(l.getImages());  // ⚠️ entity me hona chahiye

            return dto;

        }).toList();
    }


    @Override
    public FlatListing updateFlat(Long id, FlatListingDto dto) {

        FlatListing listing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        listing.setDescription(dto.getDescription());

        ListingType type = ListingType.valueOf(dto.getType().toUpperCase());
        listing.setType(type);

        if (type == ListingType.SELL) {
            listing.setPrice(dto.getPrice());
            listing.setRent(null);
        } else {
            listing.setRent(dto.getRent());
            listing.setPrice(null);
        }

        return repo.save(listing);
    }


    @Override
    public void deleteFlat(Long id) {
        FlatListing listing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        repo.delete(listing);
    }



    //============================================================== IMAGE
@Override
public void uploadImages(Long id, List<MultipartFile> files) {

    FlatListing listing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found"));

    List<String> imageNames = new ArrayList<>();

    String uploadDir = "uploads/";

    for (MultipartFile file : files) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());

            Files.write(path, file.getBytes());

            imageNames.add(fileName);

        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }
    }

    List<String> existing = listing.getImages() != null
            ? listing.getImages()
            : new ArrayList<>();

    existing.addAll(imageNames);

    listing.setImages(existing);

    repo.save(listing);

   }


    @Override
    public void deleteImage(Long id, String imageName) {

        FlatListing listing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        List<String> images = listing.getImages();

        if (images != null && images.contains(imageName)) {

            images.remove(imageName);

            try {
                Path path = Paths.get("uploads/" + imageName);
                Files.deleteIfExists(path);
            } catch (Exception e) {
                e.printStackTrace();
            }

            listing.setImages(images);
            repo.save(listing);
        }
    }


}