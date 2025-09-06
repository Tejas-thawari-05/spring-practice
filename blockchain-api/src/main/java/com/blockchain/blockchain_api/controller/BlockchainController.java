package com.blockchain.blockchain_api.controller;


import com.blockchain.blockchain_api.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    @Autowired
    private Web3j web3j;

    @GetMapping("/client-version")
    public String getClientVersion() {
        try {
            Web3ClientVersion version = web3j.web3ClientVersion().send();
            return version.getWeb3ClientVersion();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    @GetMapping("/transaction/{hash}")
    public TransactionResponse getTransactionByHash(@PathVariable String hash) {
        try {
            var txResponse = web3j.ethGetTransactionByHash(hash).send();

            if (txResponse.getTransaction().isEmpty()) {
                throw new RuntimeException("Transaction not found");
            }

            var tx = txResponse.getTransaction().get();

            var receiptResponse = web3j.ethGetTransactionReceipt(hash).send();

            String status = "Unknown";
            if (receiptResponse.getTransactionReceipt().isPresent()) {
                var receipt = receiptResponse.getTransactionReceipt().get();
                status = receipt.getStatus().equals("0x1") ? "Success" : "Failed";
            }

            // Convert Wei to Ether
            String etherValue = Convert.fromWei(tx.getValue().toString(), Convert.Unit.ETHER).toPlainString();

            TransactionResponse response = new TransactionResponse();
            response.setFrom(tx.getFrom());
            response.setTo(tx.getTo());
            response.setHash(tx.getHash());
            response.setValue(tx.getValue().toString());
            response.setValueInEther(etherValue);
            response.setBlockNumber(tx.getBlockNumber().longValue());
            response.setGas(tx.getGas().longValue());
            response.setStatus(status);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching transaction: " + e.getMessage());
        }

    }
    @GetMapping("/block/{blockNumber}/transactions")
    public List<TransactionResponse> getTransactionsInBlock(@PathVariable long blockNumber) {
        try {
            EthBlock blockResponse = web3j.ethGetBlockByNumber(
                    DefaultBlockParameter.valueOf(BigInteger.valueOf(blockNumber)),
                    true // include full transactions
            ).send();

            EthBlock.Block block = blockResponse.getBlock();
            if (block == null) {
                throw new RuntimeException("Block not found");
            }

            List<TransactionResponse> transactionList = new ArrayList<>();

            for (EthBlock.TransactionResult<?> txResult : block.getTransactions()) {
                EthBlock.TransactionObject tx = (EthBlock.TransactionObject) txResult.get();

                TransactionResponse response = new TransactionResponse();
                response.setFrom(tx.getFrom());
                response.setTo(tx.getTo());
                response.setHash(tx.getHash());
                response.setValue(tx.getValue().toString());
                response.setValueInEther(Convert.fromWei(tx.getValue().toString(), Convert.Unit.ETHER).toPlainString());
                response.setBlockNumber(tx.getBlockNumber().longValue());
                response.setGas(tx.getGas().longValue());
                response.setStatus("Unknown"); // Receipt not fetched individually here

                transactionList.add(response);
            }

            return transactionList;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching block transactions: " + e.getMessage());
        }
    }

}

