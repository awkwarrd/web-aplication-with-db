package com.example.bd_project.controller;

import com.example.bd_project.model.Reports;
import com.example.bd_project.services.ReportsService;
import com.example.bd_project.services.TransactionService;
import com.example.bd_project.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class MainScreenController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final ReportsService reportsService;

    public MainScreenController(UserService userService, TransactionService transactionService, ReportsService reportsService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.reportsService = reportsService;
    }

    @GetMapping("/")
    public String showMainPage() {
        return "main-page";
    }

    @GetMapping("/users/report")
    public String showUsersWithGoalsAndTransactions(@RequestParam("userName") String userName,  Model model) {
        System.out.println(userName);
        List<Object[]> data = userService.getUsersWithGoalsAndTransactionSums(userName);
        model.addAttribute("userGoalsTransactions", data);
        return "users-goals-transactions";
    }


    @GetMapping("/report")
    public void generateReport(HttpServletResponse response) throws IOException {

        String report = "Simple Report\n"
                + "Users Count: " + userService.countUsers() + "\n"
                + "Transactions Count: " + transactionService.countTransactions();
        System.out.println(report);

        Reports newReport = new Reports();
        newReport.setName("Simple Report");
        newReport.setGeneratedAt(LocalDateTime.now());
        newReport.setContent(report);
        reportsService.saveReport(newReport);
        System.out.println(report);

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=report.txt");
        response.getOutputStream().write(report.getBytes());
        response.getOutputStream().flush();
    }

}
