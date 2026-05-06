package com.example.demo.PaymentDueReminder.Reminder;

import java.util.List;

public interface BillItemService {

    // ➕ Add item to bill
    BillItemDto createBillItem(BillItemDto dto, Long societyId);

    // 📄 Get all items of a bill
    List<BillItemDto> getItemsByMonthlyBill(Long monthlyBillId);

    // ✏️ Update item
    BillItemDto updateBillItem(Long itemId, BillItemDto dto);

    // ❌ Delete item
    void deleteBillItem(Long itemId);

}
