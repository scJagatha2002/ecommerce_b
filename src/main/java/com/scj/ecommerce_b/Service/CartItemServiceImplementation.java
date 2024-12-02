package com.scj.ecommerce_b.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.CartItemException;
import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.CartItemRepo;
import com.scj.ecommerce_b.Repository.CartRepo;


@Service
public class CartItemServiceImplementation implements CartItemService{

    private CartItemRepo cart_item_rep;
    private UserService user_service;
    private CartRepo cart_rep;
    
    
    
    public CartItemServiceImplementation(CartItemRepo cart_item_rep, UserService user_service, CartRepo cart_rep) {
        this.cart_item_rep = cart_item_rep;
        this.user_service = user_service;
        this.cart_rep=cart_rep;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity());
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem created = cart_item_rep.save(cartItem);
        return created;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        user user=user_service.findUserById(item.getUserId());
        if(user.getId().equals(userId))
        {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }
        
        return cart_item_rep.save(item);
    }

    @Override
    public CartItem isExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cart_item_rep.isCartItemExist(cart, product, size, userId);
        return cartItem;
    }

    @Override
    public void RemoveCartItem(Long userid, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        user user=user_service.findUserById(cartItem.getUserId());
        user reqUser=user_service.findUserById(userid);
        Cart cart = cart_rep.findCartByUserId(userid);
        if(user.getId().equals(reqUser.getId()))
        {
            /*cart.setTotalDiscountedPrice(cart.getTotalDiscountedPrice()-cartItem.getDiscountedPrice());
            cart.setTotalPrice(cart.getTotalPrice()-cartItem.getPrice());
            cart.setTotalItem(cart.getTotalItem()-1);
            cart.setDiscount(cart.getDiscount()-cartItem.getDiscount());*/
            cart_item_rep.deleteById(cartItemId);
        }
        else{
            throw new UserException("You cant Remove other users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemid) throws CartItemException {
        Optional<CartItem> opt = cart_item_rep.findById(cartItemid);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new CartItemException("Item not found");
    }
    
}