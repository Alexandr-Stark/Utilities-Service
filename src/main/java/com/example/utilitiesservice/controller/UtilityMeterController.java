package com.example.utilitiesservice.controller;


import com.example.utilitiesservice.models.UtilityMeter;
import com.example.utilitiesservice.service.UtilityMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/utilityMeters")
@RequiredArgsConstructor
public class UtilityMeterController {

    private final UtilityMeterService service;

    @GetMapping
    public ResponseEntity<List<UtilityMeter>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                     @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UtilityMeter>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UtilityMeter> create(@RequestBody UtilityMeter meter) {
        return ResponseEntity.ok(service.save(meter));
    }

    @PutMapping("/update")
    public ResponseEntity<UtilityMeter> update(@RequestBody UtilityMeter meter) {
        return ResponseEntity.ok(service.save(meter));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<UtilityMeter>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
