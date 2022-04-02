package com.example.utilitiesservice.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "account_roles")
public class AccountRole {
    @Id
    @GeneratedValue()
    private Long id;
    private String roleName;
}
