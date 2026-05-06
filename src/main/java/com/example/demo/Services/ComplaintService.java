package com.example.demo.Services;

import com.example.demo.Enums.ComplaintStatus;
import com.example.demo.Payloads.ComplaintDto;

import java.util.List;

public interface ComplaintService {


// CREATE COMPLAINT
    ComplaintDto createComplaint(ComplaintDto dto);

// GET COMPLAINT
    List<ComplaintDto> getComplaints(Long societyId,Long userId, String role);


// Delete complaint method
    boolean deleteComplaint(Long complaintId, Long userId, String role);


// UPDATE COMPLAINT STATUS
     ComplaintDto updateComplaintStatus(
             Long complaintId,
           ComplaintStatus status,
           String role
    );

// UPDATE COMPLAINT
    ComplaintDto updateComplaint(
            Long complaintId,
            ComplaintDto dto,
            Long userId,
            String role
    );



}
