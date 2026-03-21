package com.digitalheroes.golf_charity_platform.controller;

import com.digitalheroes.golf_charity_platform.repository.CharityRepository;
import com.digitalheroes.golf_charity_platform.repository.ScoreRepository;
import com.digitalheroes.golf_charity_platform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    private CharityRepository charityRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Section 05: Charity Directory Features
        model.addAttribute("featuredCharities", charityRepository.findByFeaturedTrue());
        return "index"; // Looks for index.html in templates
    }

    @GetMapping("/dashboard")
public String dashboard(Authentication authentication) {
    // Check the roles of the logged-in user
    var roles = authentication.getAuthorities();
    
    for (var role : roles) {
        if (role.getAuthority().equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard"; // Redirect Admins to their panel
        }
    }
    
    return "dashboard"; // Send Subscribers to the standard dashboard
}

@GetMapping("/admin/dashboard")
@PreAuthorize("hasRole('ADMIN')")
public String adminDashboard() {
    return "admin_dashboard"; // This will be your new Admin-only HTML page
}
    @GetMapping("/login")
    public String login() {
     return "login"; // This points to templates/login.html
}
@GetMapping("/health")
@ResponseBody
public String health() { return "UP"; }
}