package com.example.utilitiesservice.controller;


import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.models.PaymentCard;
import com.example.utilitiesservice.service.PaymentCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/paymentCards")
@RequiredArgsConstructor
public class PaymentCardController {

    private final PaymentCardService service;

    @GetMapping
    public ResponseEntity<List<PaymentCard>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                    @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PaymentCard>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentCard> create(@RequestBody PaymentCard card) {
        return ResponseEntity.ok(service.save(card));
    }

    @PutMapping("/update")
    public ResponseEntity<PaymentCard> update(@RequestBody PaymentCard card) {
        return ResponseEntity.ok(service.save(card));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<PaymentCard>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
