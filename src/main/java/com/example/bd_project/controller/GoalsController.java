package com.example.bd_project.controller;

import com.example.bd_project.dto.GoalsDto;
import com.example.bd_project.model.Goals;
import com.example.bd_project.services.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/goals")
public class GoalsController {

    @Autowired
    private GoalsService goalsService;


    @GetMapping
    public List<Goals> getAllGoals() {
        return goalsService.getAllGoals();
    }

    @PostMapping
    public ResponseEntity<Goals> createGoals(@RequestBody GoalsDto goalsDto) {
        return ResponseEntity.ok(goalsService.createGoals(goalsDto));
    }

    @GetMapping("/{id}")
    public Goals getGoalsById(@PathVariable Long id) {
        return goalsService.getGoalsById(id);
    }

    @PutMapping("/{id}")
    public Goals updateGoals(@PathVariable Long id, @RequestBody GoalsDto goalsDto) {
        return goalsService.updateGoals(id, goalsDto);
    }

    @DeleteMapping("/{id}")
    public String deleteGoals(@PathVariable Long id) {
        return goalsService.deleteGoals(id);
    }
}
