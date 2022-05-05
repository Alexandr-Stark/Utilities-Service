package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.User;

public class UserStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static User getSomeUser() {
        return User.builder().id(ID)
                .name("Ryan")
                .surname("Reynolds")
                .email("ryan.reynolds@gmail.com")
                .password("4P+.63a-_[!$?fN_U9W]JWp{Fm^Qfk\"\"jC[$:saX}~nhcy6YF\"$zm5+9}7qkAM*h6Yg3@RSv(SejAN2/")
                .build();
    }
}
