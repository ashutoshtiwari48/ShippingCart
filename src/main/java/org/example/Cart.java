package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final String id;
    private final Map<String, CartItem> itemMap;
    private CartStatus cartStatus;
    private final Customer customer;

    public Cart(String id , Customer customer){
        if(id==null|| id.isBlank())
            throw new IllegalArgumentException("cart id cannot be blank");
        if(customer==null)
            throw  new IllegalArgumentException("customer cannot be null");

        this.id=id;
        this.itemMap = new HashMap<>();
        this.cartStatus = CartStatus.ACTIVE;
        this.customer = customer;
    }

    public void addItem(Product product, int quantity){
        ensureCartActive();
        if(quantity<=0)
            throw new IllegalArgumentException("Quantity must be greater than 0");

        String productId = product.getId();

        if(itemMap.containsKey(productId)){
            CartItem cartItem = itemMap.get(productId);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else{
            CartItem cartItem = new CartItem(product,quantity,product.getPrice());
            itemMap.put(productId,cartItem);
        }
    }

    public void checkout(){
        ensureCartActive();
        if(this.itemMap.isEmpty())
            throw new IllegalStateException("cannot checkout an empty cart");
        this.cartStatus = CartStatus.CHECKED_OUT;
    }

    public void abandon(){
        ensureCartActive();
        this.cartStatus = CartStatus.ABANDONED;
    }

    private void ensureCartActive(){
        if(this.cartStatus != CartStatus.ACTIVE)
            throw new IllegalStateException("cannot modify a non-active cart");
    }

    private CartItem getItemOrThrow(String productId){
        CartItem cartItem = itemMap.get(productId);
        if (cartItem == null) {
            throw new IllegalArgumentException("Item not found in cart: " + productId);
        }
        return cartItem;
    }

    public void removeItem(String productId){
        ensureCartActive();
        getItemOrThrow(productId);
        itemMap.remove(productId);
    }

    public void updateItemQuantity(String productId, int newQuantity){
        ensureCartActive();
        if(newQuantity<=0)
            throw new IllegalArgumentException("Quantity must be greater than 0");

        CartItem cartItem = getItemOrThrow(productId);
        cartItem.setQuantity(newQuantity);
    }

    public BigDecimal getSubTotal(){
        var total= BigDecimal.ZERO;

        for(CartItem cartItem:itemMap.values()){
          BigDecimal itemTotal = cartItem.getPriceAtAddition().multiply(BigDecimal.valueOf(cartItem.getQuantity())) ;
          total.add(itemTotal);
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public Customer getCustomer() {
        return customer;
    }
}
