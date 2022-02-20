package com.example.utilitiesservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.Bill 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|18:22 
@Version Bill: 1.0
*/
@Data
@Entity(name = "bills")
public class Bill {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "residence_id")
    private Residence residences;
    private String personalAccountNumber;
    @JoinTable(name = "utility_companies")
    @OneToOne
    private UtilityCompany company;
    @OneToMany(mappedBy = "bill")
    private Set<UtilityMeter> utilityMeters;
}
