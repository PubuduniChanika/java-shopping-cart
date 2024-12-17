package org.example;

import org.example.Command.AddToCartCommand;
import org.example.Factory.ProductFactory;
import org.example.Observer.ProductNotifier;
import org.example.Observer.User;
import org.example.model.CartItem;
import org.example.model.Product;
import org.example.service.ShoppingCart;
import org.example.strategy.ChristmasDiscount;
import org.example.strategy.NoDiscount;
import org.example.Decorator.GiftWrapDecorator;


public class Main {
    public static void main(String[] args) {
        // 1. Create Products using the Factory
        Product laptop = ProductFactory.createProduct("electronics", "Laptop", 1200.0);
        Product apple = ProductFactory.createProduct("grocery", "Apple", 1.2);

        System.out.println("Products created:");
        System.out.println(laptop);
        System.out.println(apple);

        // 2. Create ShoppingCart Singleton
        ShoppingCart cart = ShoppingCart.getInstance();

        // 3. Add items to cart using Commands
        CartItem laptopItem = new CartItem(laptop, 1);  // 1 Laptop
        CartItem appleItem = new CartItem(apple, 5);    // 5 Apples

        AddToCartCommand addLaptopCommand = new AddToCartCommand(laptopItem);
        AddToCartCommand addAppleCommand = new AddToCartCommand(appleItem);

        addLaptopCommand.execute();
        addAppleCommand.execute();

        System.out.println("\nItems in the cart:");
        cart.getItems().forEach(item ->
                System.out.println(item.getProduct().getName() + " x " + item.getQuantity() + " = $" + item.getTotalPrice()));

        // Undo an action (remove the apples)
        addAppleCommand.undo();
        System.out.println("\nAfter undoing apple addition:");
        cart.getItems().forEach(item ->
                System.out.println(item.getProduct().getName() + " x " + item.getQuantity() + " = $" + item.getTotalPrice()));

        // 4. Apply Discounts using Strategy
        System.out.println("\nTotal price without discount: $" + cart.getTotalPrice());

        cart.setDiscountStrategy(new ChristmasDiscount());
        System.out.println("Total price with Christmas discount: $" + cart.getTotalPrice());

        cart.setDiscountStrategy(new NoDiscount());
        System.out.println("Total price with no discount: $" + cart.getTotalPrice());

        // 5. Use Observers for Notifications
        ProductNotifier notifier = new ProductNotifier();
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        notifier.addObserver(user1);
        notifier.addObserver(user2);

        notifier.notifyObservers("New Sale: 50% off on groceries!");

        // 6. Use Decorators for Optional Features
        GiftWrapDecorator giftWrappedLaptop = new GiftWrapDecorator(laptop);
        System.out.println("\nProduct with decoration:");
        System.out.println("Description: " + giftWrappedLaptop.getDescription());
        System.out.println("Price: $" + giftWrappedLaptop.getPrice());
    }
}