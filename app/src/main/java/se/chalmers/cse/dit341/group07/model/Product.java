package se.chalmers.cse.dit341.group07.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private int price;
    private Seller seller;
    private Category category;
    private Review reviews[];
    private int imageResourceId;

    public Product (String name, String description, int price, String category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = new Category(category);
    }

    public Product (String id, String name, String description, int price, String category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = new Category(category);
    }



    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }

    public String getDescription(){
        return this.description;
    }
}

