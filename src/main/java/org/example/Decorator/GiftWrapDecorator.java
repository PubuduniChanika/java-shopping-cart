package org.example.Decorator;
import org.example.model.Product;

public class GiftWrapDecorator implements ProductDecorator {
    private Product product;

    public GiftWrapDecorator(Product product) {
        this.product = product;
    }

    @Override
    public String getDescription() {
        return product.getName() + " (Gift Wrapped)";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + 5.0;
    }
}
