package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppTransaction {
    private String txId;
    private String sender;
    private String receiver;
    private double amount;
}
