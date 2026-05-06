package com.example.demo.Payloads;

import com.example.demo.Enums.FlatStatus;

import java.util.Map;

public class FlatCountResponse {

    private Long totalFlats;

    private Map<FlatStatus, Integer> statusWiseCount;



// GETTERS & SETTERS


    public Long getTotalFlats() {
        return totalFlats;
    }

    public void setTotalFlats(Long totalFlats) {
        this.totalFlats = totalFlats;
    }

    public Map<FlatStatus, Integer> getStatusWiseCount() {
        return statusWiseCount;
    }

    public void setStatusWiseCount(Map<FlatStatus, Integer> statusWiseCount) {
        this.statusWiseCount = statusWiseCount;
    }
}
