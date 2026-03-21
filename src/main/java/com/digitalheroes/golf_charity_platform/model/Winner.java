package com.digitalheroes.golf_charity_platform.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToOne
    private Draw draw;

    private String proofImageUrl; // Screenshot of golf platform scores [cite: 85]
    
    private String status; // Pending, Approved, Paid 
    
    private Double amountWon;
}