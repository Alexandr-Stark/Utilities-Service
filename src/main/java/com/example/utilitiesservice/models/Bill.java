package com.example.utilitiesservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.Bill 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|18:22 
@Version Bill: 1.0
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bills")
public class Bill {
    @Id
    @GeneratedValue()
    @Column(name = "bill_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "residence_id")
    private Residence residence;
    private String personalAccountNumber;
    @OneToOne
    @JoinColumn(name = "company_id")
    private UtilityCompany company;
    private Long price;
}
