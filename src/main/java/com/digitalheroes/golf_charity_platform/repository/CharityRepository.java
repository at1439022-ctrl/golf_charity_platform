package com.digitalheroes.golf_charity_platform.repository;

import com.digitalheroes.golf_charity_platform.model.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CharityRepository extends JpaRepository<Charity, Long> {
    // For the featured spotlight on the homepage [cite: 83]
    List<Charity> findByFeaturedTrue();
    
    // For the search and filter directory [cite: 81]
    List<Charity> findByNameContainingIgnoreCase(String name);
}