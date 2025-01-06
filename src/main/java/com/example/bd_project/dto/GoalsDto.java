package com.example.bd_project.dto;

import com.example.bd_project.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class GoalsDto {

    private String userName;
    private String name;
    private Double targetAmount;
    private Double progress;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
