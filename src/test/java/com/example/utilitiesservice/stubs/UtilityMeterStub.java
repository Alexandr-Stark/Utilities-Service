package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.UtilityMeter;

public class UtilityMeterStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static UtilityMeter getSomeUtilityMeter() {
        return UtilityMeter.builder()
                .id(ID)
                .preliminaryIndicators(12344L)
                .currentIndicators(12345L)
                .build();
    }
}
