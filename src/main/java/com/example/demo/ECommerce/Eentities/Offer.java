package com.example.demo.ECommerce.Eentities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long buyerId;        // Member placing the offer
    private BigDecimal offerPrice;

    private LocalDateTime offerTime;

    private String status;

    private Long refId;        // productId ya flatListingId
    private String refType;


// CONSTRUCTORS
    public Offer() {}

    public Offer(Long id,Long refId, Long buyerId, BigDecimal offerPrice, LocalDateTime offerTime) {
        this.id = id;
        this.refId=refId;
        this.buyerId = buyerId;
        this.offerPrice = offerPrice;
        this.offerTime = offerTime;
    }

// GETTERS & SETTERS


    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public BigDecimal getOfferPrice() { return offerPrice; }
    public void setOfferPrice(BigDecimal offerPrice) { this.offerPrice = offerPrice; }

    public LocalDateTime getOfferTime() { return offerTime; }
    public void setOfferTime(LocalDateTime offerTime) { this.offerTime = offerTime; }
}