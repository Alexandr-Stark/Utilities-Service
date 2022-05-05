package com.example.utilitiesservice.controllers;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.services.AccountRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
public class AccountRoleController {

    private final AccountRoleService service;

    @GetMapping
    public ResponseEntity<List<AccountRole>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                    @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccountRole>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AccountRole> create(@RequestBody AccountRole role) {
        System.out.println(role);
        return ResponseEntity.ok(service.save(role));
    }

    @PutMapping("/update")
    public ResponseEntity<AccountRole> update(@RequestBody AccountRole role) {
        return ResponseEntity.ok(service.save(role));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<AccountRole>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
