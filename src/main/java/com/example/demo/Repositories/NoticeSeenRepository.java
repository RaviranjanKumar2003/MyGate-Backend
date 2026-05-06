package com.example.demo.Repositories;

import com.example.demo.Entities.NoticeSeen;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeSeenRepository extends JpaRepository<NoticeSeen, Long> {

    boolean existsByNoticeIdAndUserId(Long noticeId, Long userId);

    List<NoticeSeen> findByNoticeId(Long noticeId);

    @Transactional
    void deleteByNoticeId(Long noticeId);

    boolean existsByNoticeIdAndUserIdAndUserRole(Long noticeId, Long userId, String userRole);
}