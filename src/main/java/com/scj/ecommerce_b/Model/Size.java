package com.scj.ecommerce_b.Model;




public class Size {

    public String name;
    private int quantity;

    public Size(){
        
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Size(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    
}