package com.example.demo.Services;

import com.example.demo.Payloads.ReactionDto;

import java.util.List;
import java.util.Map;

public interface MessageReactionService {

    void toggleReaction(Long messageId, Long userId, String emoji);

    Map<String, Long> getReactions(Long messageId);

    List<ReactionDto> getReactionUsers(Long messageId);

    void removeReaction(Long messageId, Long userId);

}