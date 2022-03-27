package com.example.utilitiesservice.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/* Created by IntelliJ IDEA.
@Project Name: Utilities-Service.UtilityMeter 
@Author: Alexandr Serbeniuk
@DateTime: 20.02.2022|18:52 
@Version UtilityMeter: 1.0
*/
@Data
@Entity(name = "utility_meters")
public class UtilityMeter {
    @Id
    @GeneratedValue()
    @Column(name = "utility_meters_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
    private Long preliminaryIndicators;
    private Long currentIndicators;
    private LocalDateTime indicatorsDate;

}
