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


    /*
     * Create a new dessert object.
     *
     * @param vName is the name of the dessert
     * @param vNumber is the corresponding number of desserts
     * @param image is drawable reference ID that corresponds to the dessert
     * */
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
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

