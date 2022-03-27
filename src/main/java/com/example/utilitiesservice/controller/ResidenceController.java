package com.example.utilitiesservice.controller;


import com.example.utilitiesservice.models.Residence;
import com.example.utilitiesservice.service.ResidenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/residences")
@RequiredArgsConstructor
public class ResidenceController {

    private final ResidenceService service;

    @GetMapping
    public ResponseEntity<List<Residence>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                  @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Residence>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Residence> create(@RequestBody Residence residence) {
        return ResponseEntity.ok(service.save(residence));
    }

    @PutMapping("/update")
    public ResponseEntity<Residence> update(@RequestBody Residence residence) {
        return ResponseEntity.ok(service.save(residence));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Residence>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
