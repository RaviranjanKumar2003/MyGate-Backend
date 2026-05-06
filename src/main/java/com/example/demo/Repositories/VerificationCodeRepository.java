package com.example.demo.Repositories;

import com.example.demo.Entities.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    Optional<VerificationCode> findByUser_IdAndCodeAndUsedFalse(Long userId, String code);

    Optional<VerificationCode> findTopByCodeAndUsedFalseOrderByExpiryTimeDesc(String code);


}