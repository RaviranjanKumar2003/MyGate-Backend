package com.example.demo.Repositories;

import com.example.demo.Entities.DeletedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeletedMessageRepository
        extends JpaRepository<DeletedMessage, Long> {

    boolean existsByMessageIdAndUserId(Long messageId, Long userId);

    List<DeletedMessage> findByUserId(Long userId);
}