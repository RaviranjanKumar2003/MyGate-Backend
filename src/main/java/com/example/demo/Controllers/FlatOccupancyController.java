package com.example.demo.Controllers;

import com.example.demo.Payloads.FlatOccupancyDto;
import com.example.demo.Services.FlatOccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/occupancy")
public class FlatOccupancyController {

    @Autowired
    private FlatOccupancyService service;

    @PostMapping("/create")
    public FlatOccupancyDto create(@RequestBody FlatOccupancyDto dto) {
        return service.createOccupancy(dto);
    }

    @GetMapping("/flat/{flatId}")
    public List<FlatOccupancyDto> getByFlat(@PathVariable Long flatId) {
        return service.getByFlatId(flatId);
    }

    @GetMapping("/current/{flatId}")
    public FlatOccupancyDto current(@PathVariable Long flatId) {
        return service.getCurrentOccupant(flatId);
    }

    @PutMapping("/end/{id}")
    public FlatOccupancyDto end(@PathVariable Long id) {
        return service.endOccupancy(id);
    }
}