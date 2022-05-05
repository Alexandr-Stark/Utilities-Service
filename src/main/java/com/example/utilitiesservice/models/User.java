package com.example.utilitiesservice.models;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.User 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|17:22 
@Version User: 1.0
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue()
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String password;
    private String phone;
    @OneToOne
    @JoinColumn(name = "role_id")
    private AccountRole role;
}