package com.example.utilitiesservice.models;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.PaymentCard
@Author: Alexandr Serbeniuk
@DateTime: 26.03.2022|17:20
@Version PaymentCard: 1.0
*/

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "payment_cards")
public class PaymentCard {
    @Id
    @GeneratedValue()
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String cardNickname;
    private String cardType;
    private String cardNumber;
    private String cvc;
    private Integer month;
    private Integer year;
}
