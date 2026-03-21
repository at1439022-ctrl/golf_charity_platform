package com.digitalheroes.golf_charity_platform.service;

import com.digitalheroes.golf_charity_platform.model.User;
import com.digitalheroes.golf_charity_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Updates user status based on Stripe Webhook events
     * Source: PRD Section 04 Lifecycle 
     */
    public void handleSubscriptionChange(String email, String plan, String status) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setSubscriptionPlan(plan); // Monthly or Yearly 
            user.setSubscriptionStatus(status); // Active, Lapsed, etc. 
            userRepository.save(user);
        }
    }

    /**
     * Calculates charity cut (Min 10%)
     * Source: PRD Section 08 [cite: 77, 78]
     */
    public double calculateCharityContribution(double fee, int percentage) {
        if (percentage < 10) percentage = 10; // Enforce minimum [cite: 77]
        return fee * (percentage / 100.0);
    }
}