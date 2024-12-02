package com.scj.ecommerce_b.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scj.ecommerce_b.Model.Cart;

public interface CartRepo extends JpaRepository<Cart,Long>{
   
    @Query("SELECT c FROM Cart c WHERE c.user.id=:User_id")
    public Cart findCartByUserId(@Param("User_id") Long id);
}
