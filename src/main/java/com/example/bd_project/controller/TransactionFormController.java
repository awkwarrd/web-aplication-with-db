package com.example.bd_project.controller;

import com.example.bd_project.dto.TransactionsDto;
import com.example.bd_project.model.Transactions;
import com.example.bd_project.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class TransactionFormController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping()
    public String showTransactionPage(Model model) {
        model.addAttribute("transaction", transactionService.getAllTransactions());
        model.addAttribute("transactionDto", new TransactionsDto());
        return "transaction-table-and-form";
    }

    @PostMapping()
    public String addTransaction(@ModelAttribute("transactionDto") TransactionsDto transactionDto) {
        transactionService.createTransaction(transactionDto);
        return "redirect:/transactions"; // Перезагрузка страницы после добавления
    }

    @PostMapping("{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Transactions transaction = transactionService.getTransactionById(id);
        TransactionsDto transactionDto = new TransactionsDto();
        transactionDto.setUserName(transaction.getUser().getName());
        transactionDto.setType(transaction.getType());
        transactionDto.setCategoryName(transaction.getCategory().getName());
        transactionDto.setAmount(transaction.getAmount());
        model.addAttribute("transactionDto", transactionDto);
        model.addAttribute("transactionId", id);
        return "edit-transaction-form";
    }

    @PostMapping("/edit/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute("transactionDto") TransactionsDto transactionDto) {
        transactionService.updateTransaction(id, transactionDto);
        return "redirect:/transactions";
    }
}
