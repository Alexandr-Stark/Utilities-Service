package com.example.utilitiesservice.controllers;

import com.example.utilitiesservice.dto.UserBillDto;
import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.models.User;
import com.example.utilitiesservice.services.AccountRoleService;
import com.example.utilitiesservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final AccountRoleService accountRoleService;
    private final PasswordEncoder passwordEncoder;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                             @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody User user) {
        Long USER_ROLE = 10L;
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail()).matches()){
            return ResponseEntity.status(409).body("Wrong email address");
        }

        var existsByEmail = service.getByEmail(user.getEmail());
        if (existsByEmail != null){
            return ResponseEntity.status(409).body("User " + user.getEmail() + " already exists!");
        }
        // Role
        var role = accountRoleService.getById(USER_ROLE);
        AccountRole newRole = new AccountRole();
        newRole.setId(role.get().getId());
        newRole.setRoleName(role.get().getRoleName());
        // User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(newRole);
        return ResponseEntity.ok(createUser(user));
    }

    @PutMapping("/v1/update")
    public ResponseEntity updateV1(@RequestBody User user, @RequestParam String updatedBy) {
        Long ADMIN_ROLE = 1L;
        Optional<User> updatedByUser = service.getById(Long.valueOf(updatedBy));
        Long roleId = updatedByUser.get().getRole().getId();
        Optional<AccountRole> role = accountRoleService.getById(roleId);


        if (role.get().getId() != ADMIN_ROLE) { return ResponseEntity.status(409)
                .body("You haven't permissions for update!"); }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(service.save(user));
    }

    private Long createUser(User user) {
       Long id =  UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
       service.create(id, user.getName(), user.getSurname(), user.getMiddleName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getRole().getId());
       return id;
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
