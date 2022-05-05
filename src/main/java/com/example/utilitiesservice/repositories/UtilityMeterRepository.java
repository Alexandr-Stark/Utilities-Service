package com.example.utilitiesservice.repositories;

import com.example.utilitiesservice.models.UtilityMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityMeterRepository extends JpaRepository<UtilityMeter, Long> {
}
