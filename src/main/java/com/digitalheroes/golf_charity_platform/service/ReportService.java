package com.digitalheroes.golf_charity_platform.service;

import com.digitalheroes.golf_charity_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> getFinancialOverview() {
        // Calculate based on active subscribers
        long activeSubscribers = userRepository.count(); 
        double averageFee = 20.0; 
        double totalRevenue = activeSubscribers * averageFee;

        Map<String, Object> report = new HashMap<>();
        
        // Total prize pool (Fixed portion of each subscription)
        double totalPrizePool = totalRevenue * 0.50; 
        report.put("totalPrizePool", totalPrizePool);
        
        // Distribution of tiers based on PRD requirements
        report.put("jackpot5Match", totalPrizePool * 0.40);
        report.put("pool4Match", totalPrizePool * 0.35);
        report.put("pool3Match", totalPrizePool * 0.25);

        // Charity contribution totals (Minimum 10%)
        report.put("charityTotal", totalRevenue * 0.10); 
        report.put("totalUsers", activeSubscribers);

        return report;
    }
}