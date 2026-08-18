package org.example;

import java.math.BigDecimal;

public class CartItem {
    private final Product product;
    private int quantity;
    private final BigDecimal priceAtAddition;

    public CartItem(Product product, int quantity, BigDecimal priceAtAddition){

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (priceAtAddition.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IllegalArgumentException("Price at addition cannot be negative");
        }

        this.product=product;
        this.quantity=quantity;
        this.priceAtAddition=priceAtAddition;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPriceAtAddition() {
        return priceAtAddition;
    }
}
