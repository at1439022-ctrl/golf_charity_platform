package com.digitalheroes.golf_charity_platform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "golf_scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(1) @Max(45) // Stableford format [cite: 45]
    private Integer scoreValue;

    private LocalDate datePlayed; // [cite: 46]
}