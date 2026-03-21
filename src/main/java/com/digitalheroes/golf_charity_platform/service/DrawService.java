package com.digitalheroes.golf_charity_platform.service;

import com.digitalheroes.golf_charity_platform.model.Draw;
import com.digitalheroes.golf_charity_platform.repository.DrawRepository;
import com.digitalheroes.golf_charity_platform.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public Draw generateDraw(String type) {
        List<Integer> numbers = new ArrayList<>();
        
        if ("ALGORITHMIC".equalsIgnoreCase(type)) {
            // Weighted by most frequent user scores [cite: 59]
            numbers = generateWeightedNumbers();
        } else {
            // Standard lottery-style random draw [cite: 57, 58]
            numbers = generateRandomNumbers();
        }

        Draw draw = new Draw();
        draw.setDrawDate(LocalDateTime.now());
        draw.setWinningNumbers(numbers);
        draw.setDrawType(type);
        draw.setPublished(false); // Simulation/pre-analysis mode 
        
        return drawRepository.save(draw);
    }

    private List<Integer> generateRandomNumbers() {
        return new Random().ints(1, 46) // Range 1-45 [cite: 45]
                .distinct()
                .limit(5)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> generateWeightedNumbers() {
        // Logic to fetch all scores and weight the draw by frequency [cite: 59]
        // This is a placeholder for your custom algorithm
        
        return generateRandomNumbers(); 
    }
    /**
 * Calculates the prize pool tiers based on active subscriber count [cite: 71]
 */
public Map<String, Double> calculatePrizeTiers(double totalPool) {
    Map<String, Double> tiers = new HashMap<>();
    
    // Auto-calculation based on PRD Section 07 [cite: 69, 70]
    tiers.put("5_MATCH_JACKPOT", totalPool * 0.40);
    tiers.put("4_MATCH_POOL", totalPool * 0.35);
    tiers.put("3_MATCH_POOL", totalPool * 0.25);
    
    return tiers;
}
}