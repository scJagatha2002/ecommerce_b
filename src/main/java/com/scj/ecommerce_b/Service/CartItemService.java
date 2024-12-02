package com.scj.ecommerce_b.Service;

import com.scj.ecommerce_b.Exceptions.CartItemException;
import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;

    public CartItem isExist(Cart cart,Product product,String size,Long userId);

    public void RemoveCartItem(Long userid,Long cartItemId) throws CartItemException,UserException;

    public CartItem findCartItemById(Long cartItemid) throws CartItemException;
    
}
