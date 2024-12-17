package org.example.strategy;

public class ChristmasDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.90; // 10% off
    }
}

