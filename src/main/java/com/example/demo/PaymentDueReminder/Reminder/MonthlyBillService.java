package com.example.demo.PaymentDueReminder.Reminder;

import com.example.demo.Enums.UserRole;

import java.util.List;

public interface MonthlyBillService {

    //  Create monthly bill (manual / auto)
    MonthlyBillDto createMonthlyBill(MonthlyBillDto dto, UserRole creatorRole);


    //  Get single bill
    MonthlyBillDto getBillById(Long billId);


    //  Get bills of society
    List<MonthlyBillDto> getBillsBySociety(Long societyId);


    //  Get bills of user (Tenant / Owner)
    List<MonthlyBillDto> getBillsByUser(Long userId);

    // 🔹 Super Admin created bills
    List<MonthlyBillDto> getBillsCreatedBySuperAdmin();

    // 🔹 (Optional) society-wise
    List<MonthlyBillDto> getBillsCreatedBySuperAdminBySociety(Long societyId);

    List<MonthlyBillDto> getBillsCreatedBySocietyAdmin(Long societyId);


    //  Update status (after payment)
    MonthlyBillDto updateBillStatus(Long billId,Long societyId,Long userId,UserRole updaterRole);


    //  Delete bill (only if no payment)
    void deleteBill(Long billId);


}
