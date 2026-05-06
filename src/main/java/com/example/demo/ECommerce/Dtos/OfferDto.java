package com.example.demo.ECommerce.Dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OfferDto {

    private Long id;
    private Long buyerId;
    private BigDecimal offerPrice;
    private LocalDateTime offerTime;

    private String status;

    private String buyerName;
    private String buyerEmail;
    private String buyerMobile;

    private Long refId;
    private String refType;

// CONSTRUCTORS
    public OfferDto() {}

    public OfferDto(Long id, Long refId, Long buyerId, BigDecimal offerPrice, LocalDateTime offerTime) {
        this.id=id;
        this.refId=refId;
        this.buyerId=buyerId;
        this.offerPrice=offerPrice;
        this.offerTime=offerTime;

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

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
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