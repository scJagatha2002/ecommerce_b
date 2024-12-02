package com.scj.ecommerce_b.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.scj.ecommerce_b.Exceptions.OrderException;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.OrderItems;
import com.scj.ecommerce_b.Repository.OrderRepo;

import com.scj.ecommerce_b.Response.PaymentLinkResponse;
import com.scj.ecommerce_b.Service.OrderService;
import com.scj.ecommerce_b.Service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;

import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;

import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private OrderService order_service;

    @Autowired
    private OrderRepo order_rep;

    @Autowired
    private UserService user_service;

    
    @PostMapping("/{OrderId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long OrderId,
            @RequestHeader("Authorization") String jwt) throws OrderException, StripeException {
        Order order = order_service.find_by_id(OrderId);
        int deliveryCharge;
        int quantity = 0;
        int totalPrice;
        Stripe.apiKey = "sk_test_51OEuvmSF8Ztuvjh4ZLdONfgQoGnlqF0EyqBduyxumwnqGnjaeuhvO0EG14XqzpT1eJQFTvY1H5XWzjJ5yNcTYnAo005GdzHlXI";
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName(order.getUser().getFirstName())
                .build();
        Product product = Product.create(productParams);
        if (order.getDiscounted_price() < 1000) {
            for (OrderItems orderItems : order.getOrderList()) {
                quantity = quantity + orderItems.getQuantity();
            }
            deliveryCharge = 15 * quantity;
        } else {
            deliveryCharge = 0;
        }
        totalPrice=order.getDiscounted_price()+deliveryCharge;
        PriceCreateParams paramsPrice = PriceCreateParams
                .builder()
                .setProduct(product.getId())
                .setCurrency("inr")
                .setUnitAmount((long) (totalPrice*100))

                .build();
        Price price = Price.create(paramsPrice);

        PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(price.getId())
                                .setQuantity(1L)
                                .build())
                .setAfterCompletion(
                        PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("http://localhost:3000/payments/" + OrderId)
                                                .build())
                                .build())
                .build();
        PaymentLink paymentLink = PaymentLink.create(params);

        String paymentId = paymentLink.getId();
        String paymentUrl = paymentLink.getUrl();
        order.getPayment_details().setPaymentId(paymentId);
        order.getPayment_details().setStatus("COMPLETED");
        order.setOrderStatus("PLACED");
        order_rep.save(order);
        PaymentLinkResponse res = new PaymentLinkResponse();
        res.setPayment_link_id(paymentId);
        res.setPayment_link_url(paymentUrl);
        return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.CREATED);
    }

    
}
