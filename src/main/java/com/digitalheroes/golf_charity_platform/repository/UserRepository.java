package com.digitalheroes.golf_charity_platform.repository;

import com.digitalheroes.golf_charity_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Required for the Subscription & Payment System 
    User findByEmail(String email);
}