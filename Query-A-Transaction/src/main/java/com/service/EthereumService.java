package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.protocol.core.methods.response.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class EthereumService {

    @Autowired
    private Web3j web3j;

    @Value("${wallet.private.key}")
    private String privateKey;

    public String getClientVersion() throws IOException {
        Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
        return clientVersion.getWeb3ClientVersion();
    }

    public Optional<Transaction> getTransactionByHash(String txHash) throws IOException {
        EthTransaction transactionResponse = web3j.ethGetTransactionByHash(txHash).send();
        return transactionResponse.getTransaction();
    }

    public String sendTransaction(String toAddress, double amountEther) throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        TransactionReceipt receipt = Transfer.sendFunds(
                web3j, credentials, toAddress,
                BigDecimal.valueOf(amountEther), Convert.Unit.ETHER).send();
        return receipt.getTransactionHash();
    }
}
