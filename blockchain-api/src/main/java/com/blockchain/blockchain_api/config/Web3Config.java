package com.blockchain.blockchain_api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3Config {

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService("https://eth-sepolia.g.alchemy.com/v2/9U7l6YB-ygCG9VvhOnpbvd2O1mPqSXYB"));
    }
}
