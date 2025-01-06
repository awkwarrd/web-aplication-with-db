package com.example.bd_project.controller;

import com.example.bd_project.dto.TransactionsDto;
import com.example.bd_project.model.Category;
import com.example.bd_project.model.Transactions;
import com.example.bd_project.model.User;
import com.example.bd_project.repository.CategoryRepository;
import com.example.bd_project.repository.TransactionRepository;
import com.example.bd_project.repository.UserRepository;
import com.example.bd_project.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionsService;

    @GetMapping
    public List<Transactions> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionsDto transactionDto) {
        return ResponseEntity.ok(transactionsService.createTransaction(transactionDto));
    }

    @GetMapping("/{id}")
    public Transactions getTransactionById(@PathVariable Long id) {
        return transactionsService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public Transactions updateTransaction(@PathVariable Long id, @RequestBody TransactionsDto transactionDto) {
        return transactionsService.updateTransaction(id, transactionDto);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionsService.deleteTransaction(id);
        return "Transaction deleted";
    }
}