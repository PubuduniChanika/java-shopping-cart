package org.example.service;


import org.example.model.CartItem;
import org.example.strategy.DiscountStrategy;
import org.example.strategy.NoDiscount;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<CartItem> items;

    private ShoppingCart() {
        items = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    private DiscountStrategy discountStrategy = new NoDiscount();

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public double getTotalPrice() {
        double total = items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        return discountStrategy.applyDiscount(total);
    }

}
