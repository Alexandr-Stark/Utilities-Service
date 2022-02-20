package com.example.utilitiesservice.models;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.Residence 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|17:37 
@Version Residence: 1.0
*/

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "residences")
public class Residence {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
    private String ownerName;
    private String ownerSurname;
    private String country;
    private String city;
    private String street;
    private String house;
    private String corps;
    private String flat;
    @OneToMany(mappedBy = "residences")
    private Set<Bill> bills;
}
