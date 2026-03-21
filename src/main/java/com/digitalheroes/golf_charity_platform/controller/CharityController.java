package com.digitalheroes.golf_charity_platform.controller;

import com.digitalheroes.golf_charity_platform.model.Charity;
import com.digitalheroes.golf_charity_platform.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charities")
public class CharityController {

    @Autowired
    private CharityRepository charityRepository;

    /**
     * Get all charities for the directory listing 
     */
    @GetMapping
    public List<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    /**
     * Get featured charities for the homepage spotlight [cite: 83]
     */
    @GetMapping("/featured")
    public List<Charity> getFeaturedCharities() {
        return charityRepository.findByFeaturedTrue();
    }

    /**
     * Search charities by name 
     */
    @GetMapping("/search")
    public List<Charity> searchCharities(@RequestParam String name) {
        return charityRepository.findByNameContainingIgnoreCase(name);
    }
}