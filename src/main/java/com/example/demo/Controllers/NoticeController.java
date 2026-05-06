package com.example.demo.Controllers;

import com.example.demo.Enums.NoticeCreatedByRole;
import com.example.demo.Enums.TargetAudience;
import com.example.demo.Payloads.NoticeDto;
import com.example.demo.Repositories.NotificationRepository;
import com.example.demo.Services.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NotificationRepository notificationRepository;

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


// 1.  CREATE NOTICE BY SUPER ADMIN  FOR ALL SOCIETIES
    @PostMapping("/superAdminId/{superAdminId}/create")
    public ResponseEntity<NoticeDto> createNoticeBySuperAdmin(
            @PathVariable Integer superAdminId,
            @RequestBody NoticeDto dto
    ) throws IOException {
        return ResponseEntity.ok(
                noticeService.createNotice(dto, Long.valueOf(superAdminId), NoticeCreatedByRole.SUPER_ADMIN, null, null)
        );
    }


// 1.2 SUPER ADMIN → CREATE NOTICE FOR A SPECIFIC SOCIETY
    @PostMapping("/superAdminId/{superAdminId}/society/{societyId}/create")
    public ResponseEntity<NoticeDto> createNoticeBySuperAdminForSociety(
            @PathVariable Long superAdminId,
            @PathVariable Long societyId,
            @RequestBody NoticeDto dto
    ) throws IOException {

        return ResponseEntity.ok(
                noticeService.createNotice(
                        dto,
                        Long.valueOf(superAdminId),
                        NoticeCreatedByRole.SUPER_ADMIN,
                        societyId,
                        null
                )
        );
    }


// 2. CREATE NOTICE BY SOCIETY ADMIN
   @PostMapping("/society/{societyId}/societyAdminId/{societyAdminId}/create")
   public ResponseEntity<NoticeDto> createNoticeBySocietyAdmin(
           @PathVariable Long societyId,
           @PathVariable Long societyAdminId,
           @RequestBody NoticeDto dto
) throws IOException {

    return ResponseEntity.ok(
            noticeService.createNotice(
                    dto,
                    Long.valueOf(societyAdminId),
                    NoticeCreatedByRole.SOCIETY_ADMIN,
                    societyId,
                    null
            )
    );
}




// GET SUPER ADMIN NOTICE BY SOCIETY ADMIN
@GetMapping("/society/{societyId}/societyAdminId/{societyAdminId}")
public ResponseEntity<List<NoticeDto>> getSocietyNotices(
        @PathVariable Long societyId,
        @PathVariable Long societyAdminId
) {
    return ResponseEntity.ok(
            noticeService.getNoticesForSociety(societyId, societyAdminId)
    );
}


// GET SUPER ADMIN NOTICE BY ITSELF

    @GetMapping("/superAdminId/{superAdminId}")
    public ResponseEntity<List<NoticeDto>> getNoticesCreatedBySuperAdmin(
            @PathVariable Long superAdminId
    ) {
        List<NoticeDto> notices =
                noticeService.getNoticesCreatedBySuperAdmin(superAdminId);
        return ResponseEntity.ok(notices);
    }


// GET SOCIETY ADMIN NOTICE BY ITSELF
    @GetMapping("/societyAdminId/{societyAdminId}")
    public ResponseEntity<List<NoticeDto>> getNoticesCreatedBySocietyAdmin(
            @PathVariable Long societyAdminId
    ) {
        List<NoticeDto> notices =
                noticeService.getNoticesCreatedBySocietyAdmin(societyAdminId);
        return ResponseEntity.ok(notices);
    }


// GET SOCIETY ADMIN NOTICE BY STAFFS
@GetMapping("/society/{societyId}/staff/{staffId}")
public ResponseEntity<List<NoticeDto>> getNoticesForStaff(
        @PathVariable Long societyId,
        @PathVariable Long staffId
) {
    return ResponseEntity.ok(
            noticeService.getNoticesForStaff(societyId, staffId)
    );
}



    // Get global notices
    @GetMapping("/admin/global")
    public ResponseEntity<List<NoticeDto>> getGlobalAdminNotices(
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(
                noticeService.getGlobalAdminNotices(userId)
        );
    }


    @GetMapping("/normal-user")
    public ResponseEntity<List<NoticeDto>> getUserNotices(
            @RequestParam Long societyId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(
                noticeService.getNoticesForNormalUser(societyId, userId)
        );
    }


// Update notice
    @PutMapping("/{noticeId}")
    public ResponseEntity<NoticeDto> updateNotice(
            @PathVariable Long noticeId,
            @RequestPart("dto") String dtoString,
            @RequestParam Long userId,
            @RequestParam TargetAudience role,
            @RequestParam(required = false) Long societyId,
            @RequestPart(required = false) MultipartFile attachment
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        NoticeDto dto = mapper.readValue(dtoString, NoticeDto.class);

        return ResponseEntity.ok(
                noticeService.updateNotice(noticeId, dto, userId, role, societyId, attachment)
        );
    }


// Delete notice
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(
            @PathVariable Long noticeId,
            @RequestParam Long userId,
            @RequestParam TargetAudience role,
            @RequestParam(required = false) Long societyId
    ) {
        noticeService.deleteNotice(noticeId, userId, role, societyId);
        return ResponseEntity.ok("Notice deleted successfully");
    }


}
