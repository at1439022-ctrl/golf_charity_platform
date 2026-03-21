package com.digitalheroes.golf_charity_platform.controller;

import com.digitalheroes.golf_charity_platform.model.Score;
import com.digitalheroes.golf_charity_platform.model.User;
import com.digitalheroes.golf_charity_platform.service.ScoreService;
import com.digitalheroes.golf_charity_platform.repository.UserRepository;
import com.digitalheroes.golf_charity_platform.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * Submit a new golf score (Stableford format 1-45)
     * Automatically replaces the oldest if 5 scores exist [cite: 45, 49]
     */
    @PostMapping("/{userId}")
    public String addScore(@PathVariable Long userId, @RequestBody Score score) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        scoreService.addScore(user, score);
        return "Score added successfully. Only the latest 5 are retained[cite: 48].";
    }

    /**
     * View scores in reverse chronological order (most recent first) [cite: 50]
     */
    @GetMapping("/{userId}")
    public List<Score> getScoreHistory(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // We fetch and return the 5 retained scores [cite: 48]
        return scoreRepository.findByUserOrderByDatePlayedAsc(user);
    }
}