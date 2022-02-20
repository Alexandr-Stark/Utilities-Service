package com.example.utilitiesservice.models;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.User 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|17:22 
@Version User: 1.0
*/

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "users")
public class User {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "users")
    private Set<Residence> residences;
}
