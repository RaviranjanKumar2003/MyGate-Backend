package com.example.demo.Entities;

import com.example.demo.Enums.ListingStatus;
import com.example.demo.Enums.ListingType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FlatListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flatId;

    private Long ownerId;

    private Long societyId;

    @Enumerated(EnumType.STRING)
    private ListingType type; // SELL / RENT

    private Double price; // for SELL

    private Double rent;   // for RENT

    private String description;

    @Enumerated(EnumType.STRING)
    private ListingStatus status; // PENDING / ACTIVE / REJECTED / SOLD / RENTED

    private LocalDateTime createdAt = LocalDateTime.now();

    @ElementCollection
    private List<String> images;

// getters setters


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public ListingType getType() {
        return type;
    }

    public void setType(ListingType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public void setStatus(ListingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}