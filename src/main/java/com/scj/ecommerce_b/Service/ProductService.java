package com.scj.ecommerce_b.Service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.scj.ecommerce_b.Exceptions.ProductException;

import com.scj.ecommerce_b.Model.Product;
import com.scj.ecommerce_b.Request.ProductRequest;


public interface ProductService {

    public Product create_product(ProductRequest req);

    public String delete_product(Long id) throws ProductException;

    public Product update_product(Long id, ProductRequest req) throws ProductException;

    public Product find_product_id(Long id) throws ProductException;

    public List<Product> find_product_category(String category) throws ProductException;

    public Page<Product> get_all_product(String category, List<String> colors, List<String> size,
             Integer min_discount, String sort, String stock, Integer page_no, Integer paage_size);
             
    public Page<Product> get_products(Integer page_no, Integer paage_size);
    public List<Product> Products();

}