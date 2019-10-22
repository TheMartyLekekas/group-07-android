package se.chalmers.cse.dit341.group07.model;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

public class Product implements Serializable {
    //@SerializedName("id")
    private String id;
    private String name;
    private String description;
    private int price;
    private Seller seller;
    private Category category;

    public Product (String name, String description, int price, String category, String seller){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = new Category(category);
        this.seller = new Seller(seller);
    }

    public Product (String id, String name, String description, int price, String category, String seller){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = new Category(category);
        this.seller = new Seller(seller);
    }



    public String getId() { return this.id; }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDescription(){
        return this.description;
    }
}

