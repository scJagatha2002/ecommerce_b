package com.scj.ecommerce_b.Service;

import java.util.List;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Review;
import com.scj.ecommerce_b.Model.user;

import com.scj.ecommerce_b.Request.ReviewRequest;


public interface ReviewService {

    public Review createReview(ReviewRequest req,user user) throws ProductException;
    public List<Review> getAllReviews(Long productId);
    
}
