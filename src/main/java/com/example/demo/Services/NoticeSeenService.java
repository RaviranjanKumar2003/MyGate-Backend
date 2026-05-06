package com.example.demo.Services;

import com.example.demo.Payloads.NoticeSeenDto;

import java.util.List;

public interface NoticeSeenService {

    void markAsSeen(Long noticeId, Long userId, String role);

    List<NoticeSeenDto> getSeenUsers(Long noticeId);

}
