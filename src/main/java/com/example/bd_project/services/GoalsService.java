package com.example.bd_project.services;

import com.example.bd_project.dto.GoalsDto;
import com.example.bd_project.model.Goals;
import com.example.bd_project.model.User;
import com.example.bd_project.repository.GoalsRepository;
import com.example.bd_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

@Service
public class GoalsService {

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Goals> getAllGoals() {
        return goalsRepository.findAll();
    }

    public Goals createGoals(GoalsDto goalsDto) {
        User user = userRepository.findByName(goalsDto.getUserName()).orElseThrow(() -> new RuntimeException("User not found"));
        Goals Goals = new Goals(goalsDto, user);
        return goalsRepository.save(Goals);

    }

    public Goals getGoalsById(Long id) {
        return goalsRepository.findById(id).orElseThrow(() -> new RuntimeException("Goals not found"));
    }

    public Goals updateGoals(Long id, GoalsDto goalsDto) {
        User user = userRepository.findByName(goalsDto.getUserName()).orElseThrow(() -> new RuntimeException("User not found"));
        goalsRepository.findById(id).orElseThrow(() -> new RuntimeException("Goals not found"));
        Goals goals = new Goals(goalsDto, user);
        goals.setId(id);
        goals.setProgress(goalsDto.getProgress());
        goals.setTargetAmount(goalsDto.getTargetAmount());
        goals.setName(goalsDto.getName());
        return goalsRepository.save(goals);
    }

    public String deleteGoals(Long id) {
        goalsRepository.deleteById(id);
        return "Goals deleted";
    }
}
