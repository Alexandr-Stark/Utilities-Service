package com.example.utilitiesservice.repositories;

import com.example.utilitiesservice.models.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {

@Query("select r from residences r where ( lower(r.country) like %?2% OR lower(r.city) like %?2% OR lower(r.street) like %?2% ) AND r.user.id = ?1")
List<Residence> findByCountryOrCityOrStreet(Long userId, String parameter);
}
