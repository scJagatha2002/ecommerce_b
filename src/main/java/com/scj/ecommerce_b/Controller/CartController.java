package com.scj.ecommerce_b.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Request.AddItemRequest;
import com.scj.ecommerce_b.Response.ApiResponse;
import com.scj.ecommerce_b.Service.CartService;
import com.scj.ecommerce_b.Service.UserService;

@RestController
@RequestMapping("/api/cart")

public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart (@RequestHeader("Authorization") String jwt) throws UserException{
        user user = userService.findUserByJwt(jwt);
        Cart cart = cartService.FindUserCard(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<ApiResponse> addCart(@PathVariable Long userId,@RequestBody AddItemRequest req) throws ProductException , UserException{
        cartService.AddCart(userId, req);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);

    }
}

