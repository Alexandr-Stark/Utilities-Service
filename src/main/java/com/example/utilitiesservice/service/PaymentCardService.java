package com.example.utilitiesservice.service;

import com.example.utilitiesservice.models.AccountRole;
import com.example.utilitiesservice.models.PaymentCard;
import com.example.utilitiesservice.repository.PaymentCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentCardService {

    private final PaymentCardRepository repo;

    public List<PaymentCard> getAll(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        var cards = repo.findAll(paging);
        return cards.toList();
    }

    public Optional<PaymentCard> getById(Long id) {
        return repo.findById(id);
    }

    public PaymentCard save(PaymentCard card) {
        return repo.save(card);
    }

    public Optional<PaymentCard> delete(Long id) {
        var card = this.getById(id);
        repo.deleteById(id);
        return card;
    }
}
