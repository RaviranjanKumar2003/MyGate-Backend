package com.example.demo.Services;

import com.example.demo.Enums.VisitorStatus;
import com.example.demo.Enums.VisitorType;
import com.example.demo.Payloads.VisitorDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VisitorService {

// CREATE VISITOR
    VisitorDto createVisitor(VisitorDto visitorDto);


// GET VISITOR BY ID
    VisitorDto getVisitorById(Long societyId, Long visitorId);

// GET VISITORS BY STATUS
   List<VisitorDto> getVisitorsBySocietyAndStatus(Long societyId, VisitorStatus status);

// GET VISITORS BY Type
    List<VisitorDto> getVisitorsBySocietyAndVisitorsType(Long societyId, VisitorType visitorType);

// GET ALL VISITOR In A Society
    List<VisitorDto> getAllVisitors(Long societyId);


    List<VisitorDto> getVisitorsForOwnerTenant(
            Long societyId,
            Long buildingId,
            Long floorId,
            Long flatId
    );


    List<VisitorDto> getVisitorsForOwnerTenantByStatus(
            Long societyId,
            Long buildingId,
            Long floorId,
            Long flatId,
            VisitorStatus status
    );


//  UPDATE VISITOR
    VisitorDto updateVisitor(Long societyId,Long visitorId,VisitorDto dto);

// UPDATE VISITORS STATUS
   VisitorDto updateVisitorStatus(Long societyId, Long visitorId, VisitorStatus status);


//  DELETE VISITOR
    void deactivateVisitor(Long id, Long societyId);


// SEARCH VISITOR WITH NAME,MOBILE NUMBER,ID
    List<VisitorDto> searchVisitors(Long societyId, String keyword);

    VisitorDto updateVisitorLogo(Long visitorId, MultipartFile image);

// COUNT VISITOR BY TODAY DATE SOCIETY WISE
    long getTodayVisitorCount(Long societyId);

// COUNT VISITOR BY TODAY DATE FLAT WISE
    long getTodayVisitorCountByFlat(Long societyId, Long flatId);

}
