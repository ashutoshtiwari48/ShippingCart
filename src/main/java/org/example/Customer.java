package org.example;

public class Customer {

    private final String id;
    private final String name;
    private final String email;

    public Customer(String id, String name, String email){
        if(id==null || id.isBlank())
            throw new IllegalArgumentException("customer id cannot be blank");

        if(name==null|| name.isBlank())
            throw new IllegalArgumentException("customer name cannot be blank");
        if(email==null|| email.isBlank())
            throw new IllegalArgumentException("customer name cannot be blank");

        this.id=id;
        this.name=name;
        this.email=email;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
