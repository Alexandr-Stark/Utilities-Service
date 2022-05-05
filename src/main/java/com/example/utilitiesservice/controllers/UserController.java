package com.example.utilitiesservice.controllers;

import com.example.utilitiesservice.dto.UserBillDto;
import com.example.utilitiesservice.models.User;
import com.example.utilitiesservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                             @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(service.save(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(service.save(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<User>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/bill/{id}")
    public ResponseEntity<Optional<UserBillDto>> getBill(@PathVariable Long id){
        return ResponseEntity.ok(service.getBill(id));
    }

}
