package com.example.demo.Controllers;

import com.example.demo.Enums.NormalUserType;
import com.example.demo.Payloads.FlatHistoryDto;
import com.example.demo.Payloads.FlatOwnershipDto;
import com.example.demo.Services.FlatOwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlatOwnershipController {

    @Autowired
    private FlatOwnershipService service;

    // ASSIGN OWNER / TENANT
    @PostMapping("/flat/{flatId}/user/{userId}/assign")
    public String assign(
            @PathVariable Long flatId,
            @PathVariable Long userId,
            @RequestParam NormalUserType type,
            @RequestParam boolean isLiving
    ) {
        service.assignOwnership(flatId, userId, type, isLiving);
        return "Success";
    }

    // HISTORY
    @GetMapping("/flat/{flatId}/history")
    public List<FlatHistoryDto> history(@PathVariable Long flatId) {
        return service.getFlatHistory(flatId);
    }
}