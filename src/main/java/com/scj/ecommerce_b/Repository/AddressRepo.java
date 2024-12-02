package com.scj.ecommerce_b.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scj.ecommerce_b.Model.Address;

public interface AddressRepo extends JpaRepository<Address,Long> {
    
}
