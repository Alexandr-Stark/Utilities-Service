package com.example.utilitiesservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBillDto {
    private Long userId;
    private String userName;
    private String userSurname;
    private String userMiddleName;

    private String ownerName;
    private String ownerSurname;
    private String country;
    private String city;
    private String street;
    private String house;
    private String corps;
    private String flat;

    private String personalAccountNumber;

    private String companyName;
    private String iban;
    private String usreou;

    private Long preliminaryIndicators;
    private Long currentIndicators;
    private LocalDateTime indicatorsDate;

    private Long price;
}
