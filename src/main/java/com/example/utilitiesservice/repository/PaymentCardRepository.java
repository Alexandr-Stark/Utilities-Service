package com.example.utilitiesservice.repository;

import com.example.utilitiesservice.models.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
}
