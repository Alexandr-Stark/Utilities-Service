package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.Residence;
import com.example.utilitiesservice.models.User;

public class ResidenceStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static Residence getSomeResidence() {
        return Residence.builder().id(ID)
                .city("New-York")
                .country("USA")
                .house("100A")
                .build();
    }
}
