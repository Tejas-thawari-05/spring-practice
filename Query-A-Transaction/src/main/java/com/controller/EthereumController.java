package com.controller;

import com.entity.AppTransaction;
import com.service.EthereumService;
import com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/eth")
public class EthereumController {

    @Autowired
    private EthereumService ethereumService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/client-version")
    public String getClientVersion() throws IOException {
        return ethereumService.getClientVersion();
    }

    @GetMapping("/transaction/{txHash}")
    public org.web3j.protocol.core.methods.response.Transaction getTransactionByHash(@PathVariable String txHash) throws IOException {
        Optional<org.web3j.protocol.core.methods.response.Transaction> tx = ethereumService.getTransactionByHash(txHash);
        return tx.orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @PostMapping("/send")
    public String sendEther(@RequestParam String to, @RequestParam double amount) {
        try {
            String txHash = ethereumService.sendTransaction(to, amount);
            AppTransaction saved = new AppTransaction(txHash, "Ganache-Sender", to, amount);
            transactionService.saveTransaction(saved);
            return "Transaction sent successfully! Hash: " + txHash;
        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }
}
