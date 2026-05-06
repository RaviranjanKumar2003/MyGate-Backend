package com.example.demo.Payloads;

import com.example.demo.Enums.VisitorLogStatus;

import java.time.LocalDateTime;

public class VisitorLogDto {

    private Long id;

    private Long visitorId;

    private Long flatId;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private VisitorLogStatus visitorLogStatus;


// GETTERS & SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public VisitorLogStatus getVisitorLogStatus() {
        return visitorLogStatus;
    }

    public void setVisitorLogStatus(VisitorLogStatus visitorLogStatus) {
        this.visitorLogStatus = visitorLogStatus;
    }
}
