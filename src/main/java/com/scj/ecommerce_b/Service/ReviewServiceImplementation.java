package com.scj.ecommerce_b.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Product;

import com.scj.ecommerce_b.Model.Review;
import com.scj.ecommerce_b.Model.user;

import com.scj.ecommerce_b.Repository.ReviewRepo;

import com.scj.ecommerce_b.Request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepo review_rep;
    private ProductService product_service;

    

    public ReviewServiceImplementation(ReviewRepo review_rep, ProductService product_service) {
        this.review_rep = review_rep;
        this.product_service = product_service;
    }

    

    @Override
    public List<Review> getAllReviews(Long productId) {
        return review_rep.getAllProductReview(productId);
    }

    @Override
    public Review createReview(ReviewRequest req, user user) throws ProductException {
       Product product = product_service.find_product_id(req.getProductId());
        Review review=new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return review_rep.save(review);
    }

    
}