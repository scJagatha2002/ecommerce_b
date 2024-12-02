package com.scj.ecommerce_b.Request;



import java.util.HashSet;
import java.util.Set;

import com.scj.ecommerce_b.Model.Size;

public class ProductRequest {

    public ProductRequest(){

    }

    private String title;
    private String description;
    private int price;
    private int discount_price;
    private int discount_percent;
    private String brand;
    private String colour;
    private Set<Size> size = new HashSet<>();
    private String first_level_category;
    private String second_level_category;
    private String third_level_category;
    private String image_URL;
    private int quantity;

    public ProductRequest(String title, String description, int price, int discount_price, int discount_percent,
         String brand, String colour, Set<Size> size, String first_level_category,
            String second_level_category, String third_level_category, String image_URL, int quantity) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount_price = discount_price;
        this.discount_percent = discount_percent;
        this.brand = brand;
        this.colour = colour;
        this.size = size;
        this.first_level_category = first_level_category;
        this.second_level_category = second_level_category;
        this.third_level_category = third_level_category;
        this.image_URL = image_URL;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Set<Size> getSize() {
        return size;
    }

    public void setSize(Set<Size> size) {
        this.size = size;
    }

    public String getFirst_level_category() {
        return first_level_category;
    }

    public void setFirst_level_category(String first_level_category) {
        this.first_level_category = first_level_category;
    }

    public String getSecond_level_category() {
        return second_level_category;
    }

    public void setSecond_level_category(String second_level_category) {
        this.second_level_category = second_level_category;
    }

    public String getThird_level_category() {
        return third_level_category;
    }

    public void setThird_level_category(String third_level_category) {
        this.third_level_category = third_level_category;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    

    
}