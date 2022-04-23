package com.example.utilitiesservice.service;

import com.example.utilitiesservice.dto.UserBillDto;
import com.example.utilitiesservice.models.User;
import com.example.utilitiesservice.repository.UserRepository;
import com.example.utilitiesservice.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UtilityMeterService utilityMeterService;
    private final MappingUtils mappingUtils;

    public List<User> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var users = repo.findAll(paging);
        return users.toList();
    }

    public Optional<User> getById(Long id) {
        System.out.println(id);
        return repo.findById(id);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public Optional<User> delete(Long id) {
        var user = this.getById(id);
        repo.deleteById(id);
        return user;
    }

    public Optional<UserBillDto> getBill (Long id) {
        var meter = utilityMeterService.calculateTotalPrice(id);
        if (meter == null) return null;
        return Optional.of(mappingUtils.mapToUserBillDto(meter));
    }
}
