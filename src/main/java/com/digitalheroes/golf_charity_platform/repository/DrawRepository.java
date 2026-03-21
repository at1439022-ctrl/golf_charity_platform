package com.digitalheroes.golf_charity_platform.repository;

import com.digitalheroes.golf_charity_platform.model.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawRepository extends JpaRepository<Draw, Long> {
}