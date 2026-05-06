package com.example.demo.Services;

import com.example.demo.Enums.PaymentStatus;
import com.example.demo.Payloads.PaymentDto;
import java.util.List;
import java.util.Map;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto dto);

    List<PaymentDto> getPaymentsForUser(Long userId, String role, Long societyId);

    PaymentDto getPaymentById(Long paymentId);

    PaymentDto updatePayment(Long paymentId, PaymentDto dto);

    boolean deletePayment(Long paymentId, Long userId, String role);



    PaymentDto updatePaymentStatus(
            Long paymentId,
            PaymentStatus status,
            Long userId,
            String role,
            Long societyId
    );

    Map<String, Object> createOrder(PaymentDto dto);

    void verifyPayment(PaymentDto dto);


}
