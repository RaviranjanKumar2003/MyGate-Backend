package com.example.demo.Services;

import com.example.demo.Enums.NoticeCreatedByRole;
import com.example.demo.Payloads.NoticeDto;
import com.example.demo.Enums.TargetAudience;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

    // Create notice
    NoticeDto createNotice(
            NoticeDto dto,
            Long userId,
            NoticeCreatedByRole role,
            Long societyId,
            MultipartFile attachment
    ) throws IOException;

    // Update notice
    NoticeDto updateNotice(
            Long noticeId,
            NoticeDto dto,
            Long userId,
            TargetAudience role,
            Long societyId,
            MultipartFile attachment
    ) throws IOException;

    // Society Admin / Resident notices
    List<NoticeDto> getNoticesForSociety(Long societyId, Long societyAdminId);


    List<NoticeDto> getNoticesCreatedBySuperAdmin(Long superAdminId);

    List<NoticeDto> getNoticesCreatedBySocietyAdmin(Long societyAdminId);

    List<NoticeDto> getNoticesForStaff(Long societyId, Long userId);

    List<NoticeDto> getNoticesForNormalUser(Long societyId, Long userId);


    // Super Admin → Society Admin notices
    List<NoticeDto> getGlobalAdminNotices(Long userId);

    // Delete notice
    void deleteNotice(
            Long noticeId,
            Long loggedInUserId,
            TargetAudience loggedInRole,
            Long societyId
    );



}
