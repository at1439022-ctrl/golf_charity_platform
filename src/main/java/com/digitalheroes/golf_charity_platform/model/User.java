package com.digitalheroes.golf_charity_platform.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profiles")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    // These fields fix the errors in SubscriptionService.java
    private String subscriptionPlan;   // "Monthly" or "Yearly" 
    private String subscriptionStatus; // "Active", "Lapsed", etc. 
    
    private Long selectedCharityId; // [cite: 76]
    private int charityPercentage = 10; // Default minimum [cite: 77]
}