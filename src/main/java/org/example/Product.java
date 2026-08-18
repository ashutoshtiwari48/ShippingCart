package org.example;

import java.math.BigDecimal;

public class Product {
    private final String id ;
    private final String name;
    private final BigDecimal price;

    public Product(String id , String name, BigDecimal price){
        if(id==null || id.isBlank())
            throw new IllegalArgumentException("product id cannot be null or blank");
        if(name == null || name.isBlank())
            throw new IllegalArgumentException("product name cannot be null or blank");
        if(price.compareTo(BigDecimal.ZERO)<0)
            throw new IllegalArgumentException("product price cannot be negative");

        this.id=id;
        this.name=name;
        this.price=price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
