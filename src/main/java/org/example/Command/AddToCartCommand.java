package org.example.Command;


import org.example.model.CartItem;
import org.example.service.ShoppingCart;

public class AddToCartCommand implements Command {
    private ShoppingCart cart = ShoppingCart.getInstance();
    private CartItem item;

    public AddToCartCommand(CartItem item) {
        this.item = item;
    }

    @Override
    public void execute() {
        cart.addItem(item);
    }

    @Override
    public void undo() {
        cart.removeItem(item);
    }
}
