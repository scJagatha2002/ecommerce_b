package com.scj.ecommerce_b.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scj.ecommerce_b.Exceptions.OrderException;
import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Address;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Model.user;


public interface OrderService {
    public Order Create_order(user u,Address shippingAddress) throws ProductException;
    public Order find_by_id(Long orderId) throws OrderException;
    public List<Order> order_history(Long userId);
    public Order placed_order(Long orderId) throws OrderException;
    public Order confirmed_order(Long orderId) throws OrderException;
    public Order shipped_order(Long orderId) throws OrderException;
    public Order delivered_order(Long orderId) throws OrderException;
    public Order cancel_order(Long orderId) throws OrderException;
    public List<Order> get_all_order(String status);
    public void deleteOrder(Long orderId) throws OrderException;
    public Page<Order> get_orders(Integer page_no, Integer paage_size);    
}
