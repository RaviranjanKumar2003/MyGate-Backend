package com.example.demo.Entities;

import com.example.demo.Enums.PaymentMode;
import com.example.demo.Enums.PaymentStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= CORE ================= */

    private Double amount;

    private String description;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;   // PENDING, COMPLETED, FAILED

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode; // UPI, CARD, NET_BANKING

    /* ================= BILL LINK ================= */

    private Long billId;          // MonthlyBill ID (IMPORTANT)

    /* ================= GATEWAY ================= */

    private String provider;         // RAZORPAY
    private String orderId;           // Razorpay order_id
    private String paymentId;         // Razorpay payment_id
    private String signature;         // Razorpay signature

    private String upiApp;            // PhonePe, GPay, Paytm (only if UPI)

    /* ================= USER / SOCIETY ================= */

    private Long paidById;
    private String paidByRole;        // TENANT, OWNER

    private String payerName;

    private Long receivedById;
    private String receivedByRole;    // SOCIETY_ADMIN

    private Long societyId;
    private String societyName;

    /* ================= DATES ================= */

    private LocalDateTime paymentDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaidById(Long paidById) {
        this.paidById = paidById;
    }

    public void setReceivedById(Long receivedById) {
        this.receivedById = receivedById;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public PaymentMode setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
        return paymentMode;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUpiApp() {
        return upiApp;
    }

    public void setUpiApp(String upiApp) {
        this.upiApp = upiApp;
    }

    public Long getPaidById() {
        return paidById;
    }

    public String getPaidByRole() {
        return paidByRole;
    }

    public void setPaidByRole(String paidByRole) {
        this.paidByRole = paidByRole;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public Long getReceivedById() {
        return receivedById;
    }

    public String getReceivedByRole() {
        return receivedByRole;
    }

    public void setReceivedByRole(String receivedByRole) {
        this.receivedByRole = receivedByRole;
    }

    public Long getSocietyId() {
        return societyId;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
