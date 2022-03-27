package com.example.utilitiesservice.service;

import com.example.utilitiesservice.models.Bill;
import com.example.utilitiesservice.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repo;

    public List<Bill> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var bills = repo.findAll(paging);
        return bills.toList();
    }

    public Optional<Bill> getById(Long id) {
        return repo.findById(id);
    }

    public Bill save(Bill bill) {
        return repo.save(bill);
    }

    public Optional<Bill> delete(Long id) {
        var bill = this.getById(id);
        repo.deleteById(id);
        return bill;
    }
}
