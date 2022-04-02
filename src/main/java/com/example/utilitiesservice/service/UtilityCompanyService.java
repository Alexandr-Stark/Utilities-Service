package com.example.utilitiesservice.service;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.models.UtilityCompany;
import com.example.utilitiesservice.repository.AccountRoleRepository;
import com.example.utilitiesservice.repository.UtilityCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilityCompanyService {

    private final UtilityCompanyRepository repo;

    public List<UtilityCompany> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var companies = repo.findAll(paging);
        return companies.toList();
    }

    public Optional<UtilityCompany> getById(Long id) {
        return repo.findById(id);
    }

    public UtilityCompany save(UtilityCompany company) {
        return repo.save(company);
    }

    public Optional<UtilityCompany> delete(Long id) {
        var company = this.getById(id);
        repo.deleteById(id);
        return company;
    }
}
