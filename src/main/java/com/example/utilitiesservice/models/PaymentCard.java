package com.example.utilitiesservice.models;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.PaymentCard
@Author: Alexandr Serbeniuk
@DateTime: 26.03.2022|17:20
@Version PaymentCard: 1.0
*/

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "payment_cards")
public class PaymentCard {
    @Id
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
