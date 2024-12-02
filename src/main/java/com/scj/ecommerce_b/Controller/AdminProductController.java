package com.scj.ecommerce_b.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Request.ProductRequest;
import com.scj.ecommerce_b.Response.ApiResponse;
import com.scj.ecommerce_b.Service.ProductService;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody ProductRequest req)
    {
        Product product = productService.create_product(req);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/deleteProduct")
    public ResponseEntity<ApiResponse> deleteOrderHandler (@PathVariable Long productId,@RequestHeader("Authorization") String jwt) throws ProductException{
        productService.delete_product(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Deleted Successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/{productId}/updateProduct")
    public ResponseEntity<Product> updateProduct (@PathVariable Long productId,@RequestBody ProductRequest req) throws ProductException{
        Product product = productService.update_product(productId, req);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<ApiResponse> createMultipleProducts(@RequestBody ProductRequest [] req){

        for(ProductRequest product:req)
        {
            productService.create_product(product);
        }
        ApiResponse res = new ApiResponse();
        res.setMessage("Created successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

    
    
}
