package com.example.utilitiesservice.services;

import com.example.utilitiesservice.models.PaymentCard;
import com.example.utilitiesservice.repositories.PaymentCardRepository;
import com.example.utilitiesservice.stubs.PaymentCardStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentCardServiceTest {

    private PaymentCardService service;

    @Mock
    private PaymentCardRepository paymentCardRepository;

    @BeforeEach()
    void setup() {
        service = new PaymentCardService(paymentCardRepository);
    }

    @Test
    void testSuccessfulGetAll() {
        var card = PaymentCardStub.getSomePaymentCard();
        List<PaymentCard> cardList = new ArrayList<>(Arrays.asList(card));
        Page<PaymentCard> pageCardList = new PageImpl<>(cardList);
        Pageable paging = PageRequest.of(PaymentCardStub.PAGE, PaymentCardStub.SIZE);

        Mockito.when(paymentCardRepository.findAll(paging)).thenReturn(pageCardList);
        var result = service.getAll(PaymentCardStub.PAGE, PaymentCardStub.SIZE);

        assertEquals(result.size(), cardList.size());

    }

    @Test
    void testSuccessfulGetById() {
        var card = PaymentCardStub.getSomePaymentCard();
        Mockito.when(paymentCardRepository.findById(Mockito.any())).thenReturn(Optional.of(card));
        var result = service.getById(PaymentCardStub.ID);
        assertAll(
                () -> assertEquals(result.get().getId(), card.getId()),
                () -> assertEquals(result.get().getCardNumber(), card.getCardNumber())
        );
    }

    @Test
    void testNoSuccessfulGetById() {
        Mockito.when(paymentCardRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var result = service.getById(PaymentCardStub.ID);
        assertEquals(result, Optional.empty());

    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(PaymentCard.class);
        var card = PaymentCardStub.getSomePaymentCard();
        Mockito.when(paymentCardRepository.save(Mockito.any())).thenReturn(PaymentCardStub.getSomePaymentCard());
        var result = service.save(PaymentCardStub.getSomePaymentCard());
        Mockito.verify(paymentCardRepository, Mockito.atLeast(1)).save(captor.capture());
        assertAll(
                () -> assertEquals(card, captor.getValue()),
                () -> assertEquals(result.getCardNumber(), card.getCardNumber())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(PaymentCardStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(paymentCardRepository, Mockito.atLeast(1)).deleteById(captor.capture());
        assertEquals(PaymentCardStub.ID, captor.getValue());
    }
}
