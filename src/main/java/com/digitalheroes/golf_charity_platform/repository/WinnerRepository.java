package com.digitalheroes.golf_charity_platform.repository;

import com.digitalheroes.golf_charity_platform.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
    // This allows the WinnerController to find and save winner records 
}