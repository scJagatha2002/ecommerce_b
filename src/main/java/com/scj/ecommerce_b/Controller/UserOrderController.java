package com.scj.ecommerce_b.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.OrderException;
import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.Address;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Service.OrderService;
import com.scj.ecommerce_b.Service.UserService;

@RestController
@RequestMapping("/api/orders")
public class UserOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestHeader("Authorization") String jwt,@RequestBody Address shipping_address) throws UserException, ProductException{
        user u = userService.findUserByJwt(jwt);
        Order order = orderService.Create_order(u, shipping_address);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @GetMapping("/orderHistory")
    public ResponseEntity<List<Order>> orderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
        user u = userService.findUserByJwt(jwt);
        List<Order> order = orderService.order_history(u.getId()); 
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable Long orderId) throws OrderException{
        Order order = orderService.find_by_id(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @GetMapping("/orderAll")
    public ResponseEntity<Page<Order>> getAllOrders(@RequestParam Integer page_no, @RequestParam Integer paage_size){
        Page<Order> orders = orderService.get_orders(page_no, paage_size);
        return new ResponseEntity<>(orders, HttpStatus.OK);   
    }
}
