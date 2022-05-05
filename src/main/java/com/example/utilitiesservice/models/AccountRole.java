package com.example.utilitiesservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account_roles")
public class AccountRole {
    @Id
    @GeneratedValue()
    private Long id;
    private String roleName;
}
