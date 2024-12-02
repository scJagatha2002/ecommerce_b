package com.scj.ecommerce_b.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scj.ecommerce_b.Model.Order;

public interface OrderRepo extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.OrderStatus=:status")
    public List<Order> findOrders(@Param("status") String status);

    @Query("SELECT o FROM Order o WHERE o.User.id=:userId AND (o.OrderStatus='PLACED' OR o.OrderStatus='CONFIRMED' OR o.OrderStatus='SHIPPED' OR o.OrderStatus='DELIVERED')")
    public List<Order> getUsersOrders(@Param("userId") Long userId);

    @Query("SELECT o FROM Order o WHERE o.id=:id")
    public Order findOrderById(@Param("id") Long id);


    
}
