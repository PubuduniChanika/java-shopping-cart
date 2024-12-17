package org.example.Factory;


import org.example.model.Product;

public class ProductFactory {
    public static Product createProduct(String type, String name, double price) {
        return new Product(name, price, type);
    }
}
