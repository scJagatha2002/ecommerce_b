package com.scj.ecommerce_b.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Model.Product;

public interface OrderItemRepo extends JpaRepository<OrderItems,Long> {
   
}
