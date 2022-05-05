package com.example.utilitiesservice.stubs;

import com.example.utilitiesservice.models.AccountRole;

public class AccountRoleStub {

    public static final Long ID = 1L;
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 10;

    public static AccountRole getSomeAccountRole() {
        return AccountRole.builder().id(ID).roleName("SOME ROLE").build();
    }
}
