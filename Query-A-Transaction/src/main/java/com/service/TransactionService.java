package com.service;

import com.entity.AppTransaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final List<AppTransaction> blockchain = new ArrayList<>();

    public List<AppTransaction> getAllTransactions() {
        return blockchain;
    }

    public Optional<AppTransaction> getTransactionById(String txId) {
        return blockchain.stream()
                .filter(tx -> tx.getTxId().equalsIgnoreCase(txId))
                .findFirst();
    }

    public List<AppTransaction> getTransactionsByParticipant(String name) {
        return blockchain.stream()
                .filter(tx -> tx.getSender().equalsIgnoreCase(name) || tx.getReceiver().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void saveTransaction(AppTransaction transaction) {
        blockchain.add(transaction);
    }
}
