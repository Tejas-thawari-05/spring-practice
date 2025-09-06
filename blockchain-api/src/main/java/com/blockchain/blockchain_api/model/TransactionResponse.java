package com.blockchain.blockchain_api.model;


public class TransactionResponse {

    private String from;
    private String to;
    private String hash;
    private String value;           // in Wei
    private String valueInEther;    // human readable
    private long blockNumber;
    private long gas;
    private String status;          // Success or Failed

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getValueInEther() {
        return valueInEther;
    }
    public void setValueInEther(String valueInEther) {
        this.valueInEther = valueInEther;
    }
    public long getBlockNumber() {
        return blockNumber;
    }
    public void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }
    public long getGas() {
        return gas;
    }
    public void setGas(long gas) {
        this.gas = gas;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}