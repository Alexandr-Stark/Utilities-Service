package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.Residence;
import com.example.utilitiesservice.repositories.ResidenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResidenceService {

    private final ResidenceRepository repo;

    public List<Residence> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var residences = repo.findAll(paging);
        return residences.toList();
    }

    public List<Residence> getByCountryOrCityOrStreet(Long userId, String parameter) {
        var residences = repo.findByCountryOrCityOrStreet(userId, parameter);
        return residences;
    }

    public List<Residence>p(Long userId, String parameter) {
        var residences = repo.findByCountryOrCityOrStreet(userId, parameter);
        return residences;
    }

    public Optional<Residence> getById(Long id) {
        return repo.findById(id);
    }

    public Residence save(Residence residence) {
        return repo.save(residence);
    }

    public Optional<Residence> delete(Long id) {
        var residence = this.getById(id);
        repo.deleteById(id);
        return residence;
    }
}
