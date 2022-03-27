package com.example.utilitiesservice.repository;

import com.example.utilitiesservice.models.UtilityCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityCompanyRepository extends JpaRepository<UtilityCompany, Long> {
}
