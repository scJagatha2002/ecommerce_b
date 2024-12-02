package com.scj.ecommerce_b.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.Rating;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Request.RatingRequest;
import com.scj.ecommerce_b.Service.RatingService;
import com.scj.ecommerce_b.Service.UserService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    private RatingService ratingService;
    private UserService userService;

    

    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @PostMapping("/post")
    public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        user u = userService.findUserByJwt(jwt);
        Rating rating = ratingService.createRating(req, u);
        return new ResponseEntity<>(rating,HttpStatus.CREATED);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<List<Rating>> getAllRatingHandler(@PathVariable Long productId){
        List<Rating> r = ratingService.getProductRating(productId);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
    
}
