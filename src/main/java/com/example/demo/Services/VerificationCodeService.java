package com.example.demo.Services;

import com.example.demo.Payloads.VerificationCodeDto;
import com.example.demo.Payloads.VisitorCodeRequestDto;

public interface VerificationCodeService {

    // Resident / Owner / Tenant code generate
    VerificationCodeDto generateCode(Long userId);

    // Owner / Tenant visitor code generate
    VerificationCodeDto generateVisitorCode(VisitorCodeRequestDto request);

    // 🔥 GATE: verify ANY code (visitor + resident)
    boolean verifyAnyCode(String code);

    // (optional) sirf visitor ke liye
    boolean verifyVisitorCode(String code);

    boolean verifyCode(Long userId, String code);


}