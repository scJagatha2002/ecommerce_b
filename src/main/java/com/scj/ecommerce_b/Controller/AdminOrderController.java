package com.scj.ecommerce_b.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.CartItemException;
import com.scj.ecommerce_b.Exceptions.OrderException;
import com.scj.ecommerce_b.Exceptions.OrderItemException;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Repository.OrderItemRepo;
import com.scj.ecommerce_b.Response.ApiResponse;
import com.scj.ecommerce_b.Service.OrderItemService;
import com.scj.ecommerce_b.Service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")

public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/")
    public ResponseEntity <List<Order>> getAllOrdersHandler(@RequestParam String status){
        List<Order> orders = orderService.get_all_order(status);
        return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{orderItemId}")
    public ResponseEntity<OrderItems> findbtId(@PathVariable("orderItemId")Long orderItemId) throws OrderItemException
    {
        OrderItems o = orderItemService.findOrderItemById(orderItemId);
        return new ResponseEntity<>(o,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/confirmOrder")
    public ResponseEntity<Order> confirmOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.confirmed_order(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shipOrder")
    public ResponseEntity<Order> shipOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.shipped_order(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliverOrder")
    public ResponseEntity<Order> deliverOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.delivered_order(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancelOrder")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.cancel_order(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/deleteOrder")
    public ResponseEntity<ApiResponse> deleteOrderHandler (@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws OrderException{
        orderService.deleteOrder(orderId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Deleted Successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);

    }
}
