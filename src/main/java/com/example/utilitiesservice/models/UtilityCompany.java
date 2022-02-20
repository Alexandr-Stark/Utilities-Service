package com.example.utilitiesservice.models;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.UtilityCompanie 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|18:33 
@Version UtilityCompanie: 1.0
*/

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "utility_companies")
public class UtilityCompany  {
    @Id
    private Long id;
    private String companyName;
    private String iban;
    private String usreou;
}
