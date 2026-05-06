package com.example.demo.PaymentDueReminder.Reminder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillItemRepository extends JpaRepository<BillItem, Long> {

    // 📄 Get all items of a monthly bill
    List<BillItem> findByMonthlyBillId(Long monthlyBillId);

    // 🔢 Sum of all items amount (for auto total calculation)
    @Query("SELECT SUM(b.amount) FROM BillItem b WHERE b.monthlyBill.id = :billId")
    Double sumAmountByMonthlyBillId(Long billId);

}
