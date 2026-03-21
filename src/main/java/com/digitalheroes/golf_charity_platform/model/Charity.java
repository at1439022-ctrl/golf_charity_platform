package com.digitalheroes.golf_charity_platform.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "charities")
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(length = 1000)
    private String description; // Charity description [cite: 82]
    
    private String imageUrl; // Charity images [cite: 82]
    
    private boolean featured; // For the spotlight section on homepage [cite: 83]
}