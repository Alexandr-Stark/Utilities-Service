package com.example.utilitiesservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account_roles")
public class AccountRole {
    @Id
    private Long id;
    private String roleName;
}
