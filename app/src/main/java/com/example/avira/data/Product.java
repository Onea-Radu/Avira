package com.example.avira.data;

import android.content.Intent;

import java.io.Serializable;

public class Product implements Serializable
{
    private String name;
    private String id;
    private int price;
    private String imageUrl;
    public Product(String id,String name, int price,String imageUrl)
    {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public String getName() { return this.name; }
    public int getPrice() { return this.price; }
    public String getImageUrl() { return this.imageUrl; }

    public String getId() {
        return id;
    }
}
