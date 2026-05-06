package com.example.demo.Entities;

import com.example.demo.Enums.NormalUserType;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class FlatOccupancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flat flat;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private NormalUserType type; // OWNER / TENANT

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean active = true;
    private Boolean isLiving = true; // currently living or not

// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLiving() {
        return isLiving;
    }

    public void setLiving(Boolean living) {
        isLiving = living;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NormalUserType getType() {
        return type;
    }

    public void setType(NormalUserType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(Boolean isLiving) {
        this.isLiving = isLiving;
    }
}