package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.PaymentCard;

public class PaymentCardStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static PaymentCard getSomePaymentCard() {
        return PaymentCard.builder().id(ID)
                .cardNickname("mono black")
                .cardType("Mastercard")
                .cardNumber("4141111111111111111")
                .cvc("414")
                .month(12)
                .year(2041)
                .build();
    }
}
