package com.digitalheroes.golf_charity_platform.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "draws")
public class Draw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime drawDate; 

    @ElementCollection
    private List<Integer> winningNumbers; // The 5 numbers drawn

    private String drawType; // "RANDOM" or "ALGORITHMIC" 

    private boolean published; // Admin controls publishing 
}