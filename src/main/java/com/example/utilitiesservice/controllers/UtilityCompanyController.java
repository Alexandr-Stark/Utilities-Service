package com.example.utilitiesservice.controllers;

import com.example.utilitiesservice.models.UtilityCompany;
import com.example.utilitiesservice.services.UtilityCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/companies")
@RequiredArgsConstructor
public class UtilityCompanyController {

    private final UtilityCompanyService service;

    @GetMapping
    public ResponseEntity<List<UtilityCompany>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                       @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UtilityCompany>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UtilityCompany> create(@RequestBody UtilityCompany company) {
        return ResponseEntity.ok(service.save(company));
    }

    @PutMapping("/update")
    public ResponseEntity<UtilityCompany> update(@RequestBody UtilityCompany company) {
        return ResponseEntity.ok(service.save(company));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<UtilityCompany>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
