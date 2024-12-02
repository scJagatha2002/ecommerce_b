package com.scj.ecommerce_b.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.CartItemException;
import com.scj.ecommerce_b.Exceptions.OrderItemException;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Repository.OrderItemRepo;

@Service
public class OrderItemImplementation implements OrderItemService  {


    @Autowired
    private OrderItemRepo orderItemRepo;

    

    





    @Override
    public OrderItems createOrderItem(OrderItems orderItem) {
        
        return orderItemRepo.save(orderItem);
        
    }

    @Override
    public OrderItems findOrderItemById(Long OrderItemid) throws OrderItemException {
        Optional<OrderItems> opt = orderItemRepo.findById(OrderItemid);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new OrderItemException("Item not found");
    }
    
}
