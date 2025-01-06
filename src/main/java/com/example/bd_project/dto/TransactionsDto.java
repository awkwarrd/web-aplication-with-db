package com.example.bd_project.dto;

import com.example.bd_project.model.Category;
import com.example.bd_project.model.User;
import jakarta.persistence.*;

public class TransactionsDto {

    private String userName;
    private Double amount;
    private String categoryName;
    private String type; // dochod/wydatek

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
