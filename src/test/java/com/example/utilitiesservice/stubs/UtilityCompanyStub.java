package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.UtilityCompany;

public class UtilityCompanyStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static UtilityCompany getSomeUtilityCompany() {
        return UtilityCompany.builder()
                .id(ID)
                .companyName("Stark Eco Electro Plus")
                .iban("1234567890987654321")
                .usreou("098765432098765432")
                .build();
    }
}
