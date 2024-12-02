package com.scj.ecommerce_b.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scj.ecommerce_b.Model.user;



public interface UserRepo extends JpaRepository<user,Long> {           //Here its long becoz the type of id in user is long


    public user findByEmail(String email);

    @Query("SELECT u FROM user u")
    public List<user> get_all_users();

    
    
}