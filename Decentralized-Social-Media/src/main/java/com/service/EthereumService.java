package com.service;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;

import jakarta.annotation.PostConstruct;

@Service
public class EthereumService {

    @Value("${ethereum.privateKey}")
    private String privateKey;

    @Value("${ethereum.rpcUrl}")
    private String rpcUrl;

    @Value("${ethereum.contractAddress}")
    private String contractAddress;

    private Web3j web3j;
    private Credentials credentials;
    private PostStorage postStorage;
    


//    @PostConstruct
//    public void init() {
//        web3j = Web3j.build(new HttpService(rpcUrl));
//        credentials = Credentials.create(privateKey);
//        postStorage = PostStorage.load(contractAddress, web3j, credentials, new DefaultGasProvider());
//    }
    
    @PostConstruct
    public void init() throws IOException {
        web3j = Web3j.build(new HttpService(rpcUrl));
        credentials = Credentials.create(privateKey);
        System.out.println("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        System.out.println("Using account: " + credentials.getAddress());
        System.out.println("Contract address: " + contractAddress);

        postStorage = PostStorage.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }


    public String createPost(String content) throws Exception {
        TransactionReceipt receipt = postStorage.createPost(content).send();
        return receipt.getTransactionHash();
    }

    public BigInteger getPostCount() throws Exception {
        return postStorage.getPostCount().send();
    }

    public Tuple2<String, String> getPost(BigInteger index) throws Exception {
        return postStorage.getPost(index).send();
    }
}
