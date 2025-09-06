package com.controller;

import com.entity.AppTransaction;
import com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<AppTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{txId}")
    public AppTransaction getTransactionById(@PathVariable String txId) {
        return transactionService.getTransactionById(txId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @GetMapping("/participant/{name}")
    public List<AppTransaction> getTransactionsByParticipant(@PathVariable String name) {
        return transactionService.getTransactionsByParticipant(name);
    }
}
