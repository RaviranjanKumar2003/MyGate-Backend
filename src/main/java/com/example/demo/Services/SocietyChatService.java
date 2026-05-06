package com.example.demo.Services;

import com.example.demo.Payloads.SocietyChatDto;

import java.util.List;
import java.util.Map;

public interface SocietyChatService {

    // SEND MESSAGE
    SocietyChatDto sendMessage(SocietyChatDto dto);

    // GET ALL MESSAGES
    List<SocietyChatDto> getMessages(Long societyId,Long userId);

    // UPDATE MESSAGE (ONLY SENDER)
    SocietyChatDto updateMessage(Long societyId, Long messageId, Long senderId, String newMessage);

    // SOFT DELETE (ONLY SENDER)
    void softDeleteMessage(Long societyId, Long messageId, Long senderId);

    // HARD DELETE (ONLY SENDER)
    void hardDeleteMessage(Long societyId, Long messageId, Long senderId);

    // MARK AS SEEN
    void markMessagesAsSeen(Long societyId, Long userId);

    List<Map<String, Object>> getSeenUsers(Long messageId);

}