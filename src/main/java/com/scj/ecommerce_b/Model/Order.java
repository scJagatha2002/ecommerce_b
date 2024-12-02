package com.scj.ecommerce_b.Model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="Order_id")
    private String Order_id;

    @ManyToOne
    private user User;

    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
    private List<OrderItems> OrderList = new ArrayList<>();

    private LocalDateTime orderDate;

    private LocalDateTime DeliveryDate;

    @OneToOne
    private Address shipping_address;

    @Embedded
    private PaymentDetails payment_details;

    private double Total_Price;

    private Integer discounted_price;

    private Integer discount;

    private String OrderStatus;

    private int TotalItems;

    

    private LocalDateTime createdAt;

    public Order(){

    }

    public Order(long id, String order_id, user user, List<OrderItems> orderList, LocalDateTime orderDate,
            LocalDateTime deliveryDate, Address shipping_address, PaymentDetails payment_details, double total_Price,
            Integer discounted_price, Integer discount, String orderStatus, int totalItems, LocalDateTime createdAt) {
        this.id = id;
        this.Order_id = order_id;
        this.User = user;
        this.OrderList = orderList;
        this.orderDate = orderDate;
        this.DeliveryDate = deliveryDate;
        this.shipping_address = shipping_address;
        this.payment_details = payment_details;
        this.Total_Price = total_Price;
        this.discounted_price = discounted_price;
        this.discount = discount;
        this.OrderStatus = orderStatus;
        this.TotalItems = totalItems;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String order_id) {
        Order_id = order_id;
    }

    public user getUser() {
        return User;
    }

    public void setUser(user user) {
        User = user;
    }

    public List<OrderItems> getOrderList() {
        return OrderList;
    }

    public void setOrderList(List<OrderItems> orderList) {
        OrderList = orderList;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public Address getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(Address shipping_address) {
        this.shipping_address = shipping_address;
    }

    public PaymentDetails getPayment_details() {
        return payment_details;
    }

    public void setPayment_details(PaymentDetails payment_details) {
        this.payment_details = payment_details;
    }

    public double getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(double total_Price) {
        Total_Price = total_Price;
    }

    public Integer getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(Integer discounted_price) {
        this.discounted_price = discounted_price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public int getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(int totalItems) {
        TotalItems = totalItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    

    


}
