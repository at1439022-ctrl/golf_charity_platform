package com.digitalheroes.golf_charity_platform.repository;

import com.digitalheroes.golf_charity_platform.model.Score;
import com.digitalheroes.golf_charity_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    // Required for the Rolling 5-score logic [cite: 48, 49]
    List<Score> findByUserOrderByDatePlayedAsc(User user);
}