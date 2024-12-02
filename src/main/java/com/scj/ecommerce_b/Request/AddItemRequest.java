package com.scj.ecommerce_b.Request;

public class AddItemRequest {

    private Long ProductId;
    private String size;
    private int quantity;
    private Integer Price;

    public AddItemRequest(){

    }

    public AddItemRequest(Long productId, String size, int quantity, Integer price) {
        this.ProductId = productId;
        this.size = size;
        this.quantity = quantity;
        this.Price = price;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    
    
    
}
