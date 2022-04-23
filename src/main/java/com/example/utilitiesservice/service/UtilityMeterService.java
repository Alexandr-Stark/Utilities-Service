package com.example.utilitiesservice.service;

import com.example.utilitiesservice.models.UtilityMeter;
import com.example.utilitiesservice.repository.UtilityMeterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilityMeterService {

    private final UtilityMeterRepository repo;

    public List<UtilityMeter> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var meters = repo.findAll(paging);
        return meters.toList();
    }

    public Optional<UtilityMeter> getById(Long id) {
        return repo.findById(id);
    }

    public UtilityMeter save(UtilityMeter meter) {
        return repo.save(meter);
    }

    public Optional<UtilityMeter> delete(Long id) {
        var meter = this.getById(id);
        repo.deleteById(id);
        return meter;
    }

    public UtilityMeter calculateTotalPrice(Long id) {
        var meter = this.getById(id);

        if (meter.isEmpty()) return null;

        UtilityMeter newMeter = new UtilityMeter();

        float indicatorValue = meter.get().getCurrentIndicators() - meter.get().getPreliminaryIndicators();
        float utilityPrice = 8; // get response from Utility API
        float totalPrice = indicatorValue * utilityPrice;

        newMeter.setId(meter.get().getId());
        newMeter.setBill(meter.get().getBill());
        newMeter.setPreliminaryIndicators(meter.get().getPreliminaryIndicators());
        newMeter.setCurrentIndicators(meter.get().getCurrentIndicators());
        newMeter.setIndicatorsDate(meter.get().getIndicatorsDate());
        newMeter.getBill().setPrice((long) totalPrice);

        this.save(newMeter);

        return newMeter;

    }
}
