package se.chalmers.cse.dit341.group07.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private String description;
    private int price;
    private Seller seller;
    private Category category;
    private Review reviews[];
    private int imageResourceId;


    public Product(String productName, int price, int imageResourceId)    //, String description, Seller seller, Category category, Review reviews[]

    {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.reviews = reviews;
        this.imageResourceId = imageResourceId;
    }


    public String getProductName() {
        return this.productName;
    }

    public int getPrice() {
        return this.price;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }
}

