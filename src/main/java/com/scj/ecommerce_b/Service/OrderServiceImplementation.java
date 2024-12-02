package com.scj.ecommerce_b.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.OrderException;
import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Address;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.CartItem;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Model.PaymentDetails;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Model.Size;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.AddressRepo;
import com.scj.ecommerce_b.Repository.OrderItemRepo;
import com.scj.ecommerce_b.Repository.OrderRepo;
import com.scj.ecommerce_b.Repository.ProductRepo;
import com.scj.ecommerce_b.Repository.UserRepo;

@Service
public class OrderServiceImplementation implements OrderService {

    private AddressRepo addressRepo;
    private UserRepo userRepo;
    private CartService cartService;
    private OrderItemRepo orderItemRepo;
    private OrderRepo orderRepo;
    private ProductService productService;
    private ProductRepo productRepo;
    
   
    public OrderServiceImplementation(AddressRepo addressRepo, UserRepo userRepo, CartService cartService,
            OrderItemRepo orderItemRepo, OrderRepo orderRepo, ProductService productService, ProductRepo productRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
        this.cartService = cartService;
        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @Override
    public Order Create_order(user u, Address shippingAddress) throws ProductException {

        shippingAddress.setUser(u);
        Address address = addressRepo.save(shippingAddress);
        u.getAddress().add(address);
        userRepo.save(u);
        Cart cart=cartService.FindUserCard(u.getId());
        List<OrderItems> orderItems=new ArrayList<>();
        for(CartItem item:cart.getCartItems()){
            OrderItems orderItem = new OrderItems();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscounted_price(item.getDiscountedPrice());
            OrderItems created = orderItemRepo.save(orderItem);
            orderItems.add(created);
            Product product = productService.find_product_id(item.getProduct().getId());
            
            Set<Size> s = product.getSizes();
            int quantity = 0;
            for (Size size : s) {
                
                String si=item.getSize();
                String ze = size.getName();
                if(si.equals(ze)){
                    size.setQuantity(size.getQuantity()-item.getQuantity());
                    System.out.println("g");
                }
                quantity = quantity+size.getQuantity();
            }
            product.setSizes(s);
            product.setQuantity(quantity);
            productRepo.save(product);
            Product pro = productService.find_product_id(item.getProduct().getId());
            System.out.println("g");
            
        }
        Order createdOrder = new Order();
        createdOrder.setUser(u);
        createdOrder.setOrderList(orderItems);
        createdOrder.setTotal_Price(cart.getTotalPrice());
        createdOrder.setDiscounted_price(cart.getTotalDiscountedPrice());
        createdOrder.setTotalItems(cart.getTotalItem());
        createdOrder.setShipping_address(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setStatus("PENDING");
        createdOrder.setPayment_details(paymentDetails);
        createdOrder.setCreatedAt(LocalDateTime.now());

        Order savOrder = orderRepo.save(createdOrder);

        for(OrderItems item:orderItems){
            item.setOrder(savOrder);
            orderItemRepo.save(item);
        }
        return savOrder;
    }

    @Override
    public Order find_by_id(Long orderId) throws OrderException {
        Order order = orderRepo.findOrderById(orderId);
        if(order==null)
        {
            throw new OrderException("Order does not exist");
        }
        return order;
    }

    @Override
    public List<Order> order_history(Long userId) {
        List<Order> orders = orderRepo.getUsersOrders(userId);
        return orders;
    }

    @Override
    public Order placed_order(Long orderId) throws OrderException {
        Order order = orderRepo.findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPayment_details().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmed_order(Long orderId) throws OrderException {
        Order order = find_by_id(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepo.save(order);
    }

    @Override
    public Order shipped_order(Long orderId) throws OrderException {
        Order order = find_by_id(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepo.save(order);
    }

    @Override
    public Order delivered_order(Long orderId) throws OrderException {
        Order order = find_by_id(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepo.save(order);
    }

    @Override
    public Order cancel_order(Long orderId) throws OrderException {
        Order order = find_by_id(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepo.save(order);
    }

    @Override
    public List<Order> get_all_order(String status) {
        return orderRepo.findOrders(status);
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order = find_by_id(orderId);
        orderRepo.delete(order);
    }

    @Override
    public Page<Order> get_orders(Integer page_no, Integer paage_size) {
        Pageable pageable = PageRequest.of(page_no, paage_size);
        List<Order> orders = orderRepo.findAll();

        int startIdx = (int) pageable.getOffset();
        int endIdx = Math.min(startIdx + pageable.getPageSize(), orders.size());
        List<Order> pageContent = orders.subList(startIdx, endIdx);
        Page<Order> filteredOrders = new PageImpl<>(pageContent, pageable, orders.size());
        return filteredOrders;
    }

    
}
