package com.scj.ecommerce_b.Service;

import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Model.user;

import com.scj.ecommerce_b.Repository.CartRepo;
import com.scj.ecommerce_b.Request.AddItemRequest;

@Service
public class CartServiceImplementation  implements CartService{

    private CartRepo cart_rep;
    private ProductService productService;
    private CartItemService cartItemService;

    

    public CartServiceImplementation(CartRepo cart_rep,  ProductService productService,
            CartItemService cartItemService) {
        this.cart_rep = cart_rep;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @Override
    public Cart CreateCart(user u) {
        Cart cart=new Cart();
        cart.setUser(u);
        return cart_rep.save(cart);
    }

    @Override
    public String AddCart(Long userId, AddItemRequest req) throws ProductException {
        Cart caart=cart_rep.findCartByUserId(userId);
        Product product=productService.find_product_id(req.getProductId());
        CartItem isPresent=cartItemService.isExist(caart, product,req.getSize(), userId);
        if(isPresent==null)
        {
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(caart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);

            int price=req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            caart.getCartItems().add(createdCartItem);
        }
        return "Item added to cart";
    }

    @Override
    public Cart FindUserCard(Long userId) {
        Cart cart=cart_rep.findCartByUserId(userId);
        int totalPrice=0;
        int totalDiscountPrice=0;
        int totalItem=0;
       

        for(CartItem cartItem:cart.getCartItems()){
            totalPrice+=cartItem.getPrice();
            totalDiscountPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
            
        }

        cart.setTotalDiscountedPrice(totalDiscountPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
       
        

        return cart_rep.save(cart);
    }
    
}