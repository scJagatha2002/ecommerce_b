package com.scj.ecommerce_b.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Model.Rating;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.RatingRepo;
import com.scj.ecommerce_b.Request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {

    private RatingRepo rating_rep;
    private ProductService product_service;

    

    public RatingServiceImplementation(RatingRepo rating_rep, ProductService product_service) {
        this.rating_rep = rating_rep;
        this.product_service = product_service;
    }

    @Override
    public Rating createRating(RatingRequest req, user user) throws ProductException {
        Product product = product_service.find_product_id(req.getProductId());
        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return rating_rep.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {

        return rating_rep.getAllProductRating(productId);
    }
    
}