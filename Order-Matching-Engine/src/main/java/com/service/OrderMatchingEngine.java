package com.service;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import com.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderMatchingEngine {
	private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(Comparator.comparing(Order::getPrice).reversed());
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(Comparator.comparing(Order::getPrice));

    private final ReentrantLock lock = new ReentrantLock();

    public void processOrder(Order order) {
        lock.lock();
        try {
            if ("BUY".equals(order.getSide())) {
                matchOrder(order, sellOrders, buyOrders);
            } else {
                matchOrder(order, buyOrders, sellOrders);
            }
        } finally {
            lock.unlock();
        }
    }

    private void matchOrder(Order order, PriorityQueue<Order> oppositeOrders, PriorityQueue<Order> sameSideOrders) {
        while (!oppositeOrders.isEmpty() && order.getQuantity() > 0) {
            Order topOrder = oppositeOrders.peek();

            if ((order.getSide().equals("BUY") && order.getPrice().compareTo(topOrder.getPrice()) >= 0) ||
                (order.getSide().equals("SELL") && order.getPrice().compareTo(topOrder.getPrice()) <= 0)) {

                int tradeQty = Math.min(order.getQuantity(), topOrder.getQuantity());
                log.info("Trade Executed: {} {} @ {}", tradeQty, order.getSymbol(), topOrder.getPrice());

                order.setQuantity(order.getQuantity() - tradeQty);
                topOrder.setQuantity(topOrder.getQuantity() - tradeQty);

                if (topOrder.getQuantity() == 0) {
                    oppositeOrders.poll();
                }
            } else {
                break;
            }
        }
        if (order.getQuantity() > 0) {
            sameSideOrders.offer(order);
        }
    }
}