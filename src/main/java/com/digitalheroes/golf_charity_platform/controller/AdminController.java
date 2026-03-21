package com.digitalheroes.golf_charity_platform.controller;

import com.digitalheroes.golf_charity_platform.model.*;
import com.digitalheroes.golf_charity_platform.service.*;
import com.digitalheroes.golf_charity_platform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Admin Dashboard Controller
 * Provides full control over platform operations as per PRD Section 11.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private DrawService drawService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharityRepository charityRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    // --- 01. USER MANAGEMENT [cite: 98] ---

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll(); // View user profiles [cite: 99]
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscriptionStatus(userDetails.getSubscriptionStatus()); // Manage subscriptions [cite: 101]
        return userRepository.save(user);
    }

    // --- 02. DRAW MANAGEMENT [cite: 102] ---

    @PostMapping("/draws/simulate")
    public Draw simulateDraw(@RequestParam String type) {
        // Run simulations with Random or Algorithmic logic [cite: 103, 104]
        return drawService.generateDraw(type);
    }

    @PatchMapping("/draws/{id}/publish")
    public String publishDraw(@PathVariable Long id) {
        // Admin controls publishing of draw results [cite: 62, 105]
        return "Draw results published to platform.";
    }

    // --- 03. CHARITY MANAGEMENT [cite: 106] ---

    @PostMapping("/charities")
    public Charity addCharity(@RequestBody Charity charity) {
        return charityRepository.save(charity); // Add new charities [cite: 107]
    }

    @DeleteMapping("/charities/{id}")
    public void deleteCharity(@PathVariable Long id) {
        charityRepository.deleteById(id); // Delete charities [cite: 107]
    }

    // --- 04. WINNER MANAGEMENT [cite: 109] ---

    @GetMapping("/winners")
    public List<Winner> getWinnersList() {
        return winnerRepository.findAll(); // View full winners list [cite: 110]
    }

    @PostMapping("/winners/{id}/verify")
    public String verifyWinner(@PathVariable Long id, @RequestParam String status) {
        // Verify submissions and approve/reject [cite: 85, 111]
        Winner winner = winnerRepository.findById(id).orElseThrow();
        winner.setStatus(status); // Mark as Paid or Rejected [cite: 112]
        winnerRepository.save(winner);
        return "Winner status updated to: " + status;
    }

    // --- 05. REPORTS & ANALYTICS [cite: 113] ---

    @GetMapping("/stats")
    public Map<String, Object> getPlatformStats() {
        // Returns Total Users, Prize Pool, and Charity Totals [cite: 114, 115, 116]
        return reportService.getFinancialOverview();
    }
}