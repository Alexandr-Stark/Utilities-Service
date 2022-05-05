package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.repositories.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountRoleService {

    private final AccountRoleRepository repo;

    public List<AccountRole> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var roles =  repo.findAll(paging);
        return roles.toList();
    }

    public Optional<AccountRole> getById(Long id) {
        return repo.findById(id);
    }

    public AccountRole save(AccountRole role) {
        return repo.save(role);
    }

    public Optional<AccountRole> delete(Long id) {
        var role = this.getById(id);
        repo.deleteById(id);
        return role;
    }
}
