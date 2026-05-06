package com.example.demo.Payloads;

import com.example.demo.Enums.PaymentStatus;
import com.example.demo.Enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentDto {

    private Long id;
    private Double amount;
    private String description;
    private PaymentStatus status;
    private PaymentType paymentType;
    private String societyName;
    private String payerName;
    private Long  paidById;
    private String paidByRole;
    private Long receivedById;
    private String receivedByRole;
    private Long societyId;
    private String paidByName;
    private String receivedByName;
    private LocalDateTime paymentDate;

    private Long billId;

    // Razorpay response
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private String paymentMode;

    private String upiApp;      // PhonePe, GPay

// Getters & Setters


    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public String getUpiApp() {
        return upiApp;
    }

    public void setUpiApp(String upiApp) {
        this.upiApp = upiApp;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPaidById() {
        return paidById;
    }

    public void setPaidById(Long paidById) {
        this.paidById = paidById;
    }

    public String getPaidByRole() {
        return paidByRole;
    }

    public void setPaidByRole(String paidByRole) {
        this.paidByRole = paidByRole;
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

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getPaidByName() {
        return paidByName;
    }

    public void setPaidByName(String paidByName) {
        this.paidByName = paidByName;
    }

    public String getReceivedByName() {
        return receivedByName;
    }

    public void setReceivedByName(String receivedByName) {
        this.receivedByName = receivedByName;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
