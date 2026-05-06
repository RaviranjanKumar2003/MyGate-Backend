package com.example.demo.PaymentDueReminder.Reminder;

import lombok.Data;

@Data
public class BillItemDto {

    private Long id;

    private Long monthlyBillId;

    private String title;        // Maintenance, Water, Parking
    private String description;  // Optional details

    private Double amount;


// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonthlyBillId() {
        return monthlyBillId;
    }

    public void setMonthlyBillId(Long monthlyBillId) {
        this.monthlyBillId = monthlyBillId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
