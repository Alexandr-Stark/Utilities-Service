package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.Bill;
import com.example.utilitiesservice.models.PaymentCard;

public class BillStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static Bill getSomeBill() {
        return Bill.builder().id(ID)
                .personalAccountNumber("1600034214565")
                .price(120L)
                .build();
    }
}
