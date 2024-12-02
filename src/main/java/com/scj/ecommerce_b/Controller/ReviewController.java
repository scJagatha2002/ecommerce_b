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

import com.scj.ecommerce_b.Model.Review;
import com.scj.ecommerce_b.Model.user;

import com.scj.ecommerce_b.Request.ReviewRequest;

import com.scj.ecommerce_b.Service.ReviewService;
import com.scj.ecommerce_b.Service.UserService;


@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;

    

    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/post")
    public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        user u = userService.findUserByJwt(jwt);
        Review review = reviewService.createReview(req, u);
        return new ResponseEntity<>(review,HttpStatus.CREATED);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<List<Review>> getAllReviewHandler(@PathVariable Long productId){
        List<Review> r = reviewService.getAllReviews(productId);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
    
}
