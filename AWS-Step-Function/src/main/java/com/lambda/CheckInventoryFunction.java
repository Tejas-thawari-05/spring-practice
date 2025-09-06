package com.lambda;

import java.util.Map;

public class CheckInventoryFunction {
    public Map<String, String> handleRequest(Map<String, String> event) {
        String orderId = event.get("orderId");
        return Map.of("orderId", orderId, "status", "Inventory Checked");
    }
}
