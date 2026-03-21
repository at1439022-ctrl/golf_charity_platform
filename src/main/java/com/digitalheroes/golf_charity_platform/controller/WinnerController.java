package com.digitalheroes.golf_charity_platform.controller;

import com.digitalheroes.golf_charity_platform.model.Winner;
import com.digitalheroes.golf_charity_platform.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/winners")
public class WinnerController {

    @Autowired
    private WinnerRepository winnerRepository;

    /**
     * Winners upload a screenshot of scores for admin review
     * Source: PRD Section 09 
     */
    @PostMapping("/{winnerId}/upload-proof")
    public String uploadProof(@PathVariable Long winnerId, @RequestParam String imageUrl) {
        Winner winner = winnerRepository.findById(winnerId)
                .orElseThrow(() -> new RuntimeException("Winner record not found"));
        
        winner.setProofImageUrl(imageUrl);
        winner.setStatus("PENDING"); // State: Pending 
        winnerRepository.save(winner);
        
        return "Proof uploaded. Awaiting Admin Review.";
    }
}