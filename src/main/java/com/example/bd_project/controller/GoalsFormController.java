package com.example.bd_project.controller;

import com.example.bd_project.dto.GoalsDto;
import com.example.bd_project.model.Goals;
import com.example.bd_project.services.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/goals")
public class GoalsFormController {

    @Autowired
    private GoalsService goalsService;

    @GetMapping()
    public String showGoalsPage(Model model) {
        model.addAttribute("goal", goalsService.getAllGoals());
        model.addAttribute("goalDto", new GoalsDto());
        return "goals-table-and-form";
    }

    @PostMapping()
    public String addGoals(@ModelAttribute("goalDto") GoalsDto goalDto) {
        goalsService.createGoals(goalDto);
        return "redirect:/goals"; // Перезагрузка страницы после добавления
    }

    @PostMapping("{id}")
    public String deleteGoals(@PathVariable Long id) {
        goalsService.deleteGoals(id);
        return "redirect:/goals";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Goals goal = goalsService.getGoalsById(id);
        GoalsDto goalDto = new GoalsDto();
        goalDto.setUserName(goal.getUser().getName());
        goalDto.setName(goal.getName());
        goalDto.setProgress(goal.getProgress());
        goalDto.setTargetAmount(goal.getTargetAmount());
        model.addAttribute("goalDto", goalDto);
        model.addAttribute("goalId", id);
        return "edit-goal-form";
    }

    @PostMapping("/edit/{id}")
    public String updateGoals(@PathVariable Long id, @ModelAttribute("goalDto") GoalsDto goalDto) {
        goalsService.updateGoals(id, goalDto);
        return "redirect:/goals";
    }
}
