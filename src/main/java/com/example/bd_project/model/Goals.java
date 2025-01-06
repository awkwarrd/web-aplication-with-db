package com.example.bd_project.model;

import com.example.bd_project.dto.GoalsDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "goals")
public class Goals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    private Double targetAmount;

    private Double progress;

    private LocalDateTime deadline;

    public Goals(GoalsDto goalsDto, User user) {
        this.user = user;
        this.name = goalsDto.getName();
        this.targetAmount = goalsDto.getTargetAmount();
        this.progress = goalsDto.getProgress();
        this.deadline = LocalDateTime.now();
    }

    public Goals() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}

