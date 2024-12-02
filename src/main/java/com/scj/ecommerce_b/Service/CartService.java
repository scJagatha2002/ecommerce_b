package com.scj.ecommerce_b.Service;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Request.AddItemRequest;

public interface CartService {

    public Cart CreateCart(user u);
    public String AddCart(Long userId,AddItemRequest req) throws ProductException;
    public Cart FindUserCard(Long userId);
}
