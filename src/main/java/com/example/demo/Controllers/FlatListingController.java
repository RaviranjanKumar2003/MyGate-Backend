package com.example.demo.Controllers;

import com.example.demo.Entities.FlatListing;
import com.example.demo.Payloads.FlatListingDto;
import com.example.demo.Payloads.FlatListingResponseDto;
import com.example.demo.Services.FlatListingService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/flat-listings")
public class FlatListingController {

    private final FlatListingService service;


    public FlatListingController(FlatListingService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public FlatListing create(@RequestBody FlatListingDto dto) {
        return service.createListing(dto);
    }

    @GetMapping("/owner/{ownerId}")
    public List<FlatListing> getByOwner(@PathVariable Long ownerId) {
        return service.getByOwner(ownerId);
    }

    @PutMapping("/{id}/status")
    public FlatListing updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/pending/{societyId}")
    public List<FlatListing> getPending(@PathVariable Long societyId) {
        return service.getPendingBySociety(societyId);
    }

    @GetMapping("/society/{societyId}")
    public List<FlatListingResponseDto> getBySociety(@PathVariable Long societyId) {
        return service.getAllBySociety(societyId);
    }


    @DeleteMapping("/{id}")
    public String deleteFlat(@PathVariable Long id) {
        service.deleteFlat(id);
        return "Flat deleted successfully";
    }

    @PutMapping("/{id}")
    public FlatListing updateFlat(
            @PathVariable Long id,
            @RequestBody FlatListingDto dto
    ) {
        return service.updateFlat(id, dto);
    }


//===================================================================== IMAGE
    @PostMapping("/flat-listings/{id}/images")
    public String uploadFlatImages(
            @PathVariable Long id,
            @RequestParam("images") List<MultipartFile> files
    ) {
        service.uploadImages(id, files);
        return "Images uploaded";
    }


    @GetMapping("/flat/image/{id}/{img}")
    public ResponseEntity<Resource> getImage(
            @PathVariable Long id,
            @PathVariable String img
    ) throws MalformedURLException {

        Path path = Paths.get("uploads/" + img);
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    @DeleteMapping("/{id}/image")
    public String deleteImage(
            @PathVariable Long id,
            @RequestParam String imageName
    ) {
        service.deleteImage(id, imageName);
        return "Image removed";
    }
}