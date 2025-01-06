package com.example.bd_project.services;

import com.example.bd_project.dto.TransactionsDto;
import com.example.bd_project.model.Category;
import com.example.bd_project.model.Transactions;
import com.example.bd_project.model.User;
import com.example.bd_project.repository.CategoryRepository;
import com.example.bd_project.repository.TransactionRepository;
import com.example.bd_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transactions createTransaction(TransactionsDto transactionDto) {
        User user = userRepository.findByName(transactionDto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findByName(transactionDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Transactions transaction = new Transactions(transactionDto, user, category);
        return transactionRepository.save(transaction);
    }

    public Transactions getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public Transactions updateTransaction(Long id, TransactionsDto transactionDto) {
        Transactions transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        userRepository.findByName(transactionDto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        categoryRepository.findByName(transactionDto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        transaction.setAmount(transactionDto.getAmount());
        transaction.setType(transactionDto.getType());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public long countTransactions() {
        return transactionRepository.count();
    }

}
