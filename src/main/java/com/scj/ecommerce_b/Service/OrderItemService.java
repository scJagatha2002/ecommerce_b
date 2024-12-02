package com.scj.ecommerce_b.Service;

import com.scj.ecommerce_b.Exceptions.CartItemException;
import com.scj.ecommerce_b.Exceptions.OrderItemException;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.OrderItems;

public interface OrderItemService {

    public OrderItems createOrderItem(OrderItems orderItem);
    public OrderItems findOrderItemById(Long OrderItemid) throws OrderItemException;
}
