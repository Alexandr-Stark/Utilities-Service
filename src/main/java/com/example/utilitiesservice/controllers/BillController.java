package com.example.utilitiesservice.controllers;


import com.example.utilitiesservice.models.Bill;
import com.example.utilitiesservice.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping
    public ResponseEntity<List<Bill>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                              @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bill>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Bill> create(@RequestBody Bill bill) {
        return ResponseEntity.ok(service.save(bill));
    }

    @PutMapping("/update")
    public ResponseEntity<Bill> update(@RequestBody Bill bill) {
        return ResponseEntity.ok(service.save(bill));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Bill>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
