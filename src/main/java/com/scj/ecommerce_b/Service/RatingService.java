package com.scj.ecommerce_b.Service;

import java.util.List;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Rating;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Request.RatingRequest;

public interface RatingService  {

    public Rating createRating(RatingRequest req,user user) throws ProductException;
    public List<Rating> getProductRating(Long productId);
    
    
}
