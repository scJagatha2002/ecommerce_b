package com.scj.ecommerce_b.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductsHandler(@RequestParam String category, @RequestParam List<String> colors, @RequestParam List<String> size,
             @RequestParam Integer min_discount, @RequestParam String sort, @RequestParam String stock, @RequestParam Integer page_no, @RequestParam Integer paage_size) {
        Page<Product> product = productService.get_all_product(category, colors, size,
                min_discount, sort, stock, page_no, paage_size);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Product> findByIdHandler(@PathVariable Long productId) throws ProductException{
        Product product = productService.find_product_id(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/productAll")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam Integer page_no, @RequestParam Integer paage_size){
        Page<Product> products = productService.get_products(page_no, paage_size);
        return new ResponseEntity<>(products, HttpStatus.OK);   
    }

    @GetMapping("/Allproduct")
    public ResponseEntity<List<Product>> getProductAll(){
        List<Product> products = productService.Products();
        return new ResponseEntity<>(products, HttpStatus.OK);   
    }

    
}
