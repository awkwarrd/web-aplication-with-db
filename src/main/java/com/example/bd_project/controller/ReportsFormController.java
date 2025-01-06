package com.example.bd_project.controller;

import com.example.bd_project.dto.ReportsDto;
import com.example.bd_project.model.Reports;
import com.example.bd_project.model.User;
import com.example.bd_project.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.bd_project.dto.UserDto;
import com.example.bd_project.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportsFormController {

    @Autowired
    private ReportsService reportService;

    @GetMapping()
    public String showUsersPage(Model model) {
        model.addAttribute("reports", reportService.getAllReports());
        model.addAttribute("reportsDto", new Reports());
        return "reports-form";
    }



}
