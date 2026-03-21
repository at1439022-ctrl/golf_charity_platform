package com.digitalheroes.golf_charity_platform.service;

import com.digitalheroes.golf_charity_platform.model.Score;
import com.digitalheroes.golf_charity_platform.model.User;
import com.digitalheroes.golf_charity_platform.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public void addScore(User user, Score newScore) {
        // [cite: 48] Only the latest 5 scores are retained
        List<Score> history = scoreRepository.findByUserOrderByDatePlayedAsc(user);

        // [cite: 49] A new score replaces the oldest stored score automatically
        if (history.size() >= 5) {
            scoreRepository.delete(history.get(0));
        }

        newScore.setUser(user);
        scoreRepository.save(newScore);
    }
}