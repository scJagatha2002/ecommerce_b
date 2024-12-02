package com.scj.ecommerce_b.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.ProductException;
import com.scj.ecommerce_b.Model.Category;
import com.scj.ecommerce_b.Model.Product;

import com.scj.ecommerce_b.Repository.CategoryRepo;
import com.scj.ecommerce_b.Repository.ProductRepo;
import com.scj.ecommerce_b.Request.ProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

    private CategoryRepo category_rep;
    private ProductRepo product_rep;

    public ProductServiceImplementation(CategoryRepo category_rep, ProductRepo product_rep) {
        this.category_rep = category_rep;
        this.product_rep = product_rep;
    }

    @Override
    public Product create_product(ProductRequest req) {

        Category top_level = category_rep.findByName(req.getFirst_level_category());
        if (top_level == null) {
            Category first_level = new Category();
            first_level.setName(req.getFirst_level_category());
            first_level.setLevel(1);
            top_level=category_rep.save(first_level);
        }

        Category mid_level = category_rep.findByNameAndParent(req.getSecond_level_category(),top_level.getName());
        if (mid_level == null) {
            Category second_level = new Category();
            second_level.setName(req.getSecond_level_category());
            second_level.setParentCategory(top_level);
            second_level.setLevel(2);
            mid_level=category_rep.save(second_level);
        }

        Category last_level = category_rep.findByNameAndParent(req.getThird_level_category(),mid_level.getName());
        if (last_level == null) {
            Category third_level = new Category();
            third_level.setName(req.getThird_level_category());
            third_level.setParentCategory(mid_level);
            third_level.setLevel(3);
            last_level=category_rep.save(third_level);
        }

        

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setDiscountedPrice(req.getDiscount_price());
        product.setDiscountedPercent(req.getDiscount_percent());
        product.setQuantity(req.getQuantity());
        product.setBrand(req.getBrand());
        product.setColor(req.getColour());
        product.setSizes(req.getSize());
        product.setImage_URL(req.getImage_URL());
        product.setCreatedAt(LocalDateTime.now());
        product.setCategory(last_level);
        return product_rep.save(product);
    }

    @Override
    public String delete_product(Long id) throws ProductException {
        Optional<Product> product = product_rep.findById(id);
        if (product.isPresent()) {
            product_rep.deleteById(id);
            return "Product Deleted Successfully";
        }
        throw new ProductException("Product not found");

    }

    @Override
    public Product update_product(Long id, ProductRequest req) throws ProductException {
        Product product = product_rep.findProductById(id);
        if (product != null) {
            Category top_level = category_rep.findByName(req.getFirst_level_category());
            if (top_level == null) {
                Category first_level = new Category();
                first_level.setName(req.getFirst_level_category());
                first_level.setLevel(1);
                category_rep.save(first_level);
            }

            Category mid_level = category_rep.findByName(req.getSecond_level_category());
            if (mid_level == null) {
                Category second_level = new Category();
                second_level.setName(req.getSecond_level_category());
                second_level.setLevel(2);
                category_rep.save(second_level);
            }

            Category last_level = category_rep.findByName(req.getThird_level_category());
            if (last_level == null) {
                Category third_level = new Category();
                third_level.setName(req.getThird_level_category());
                third_level.setLevel(3);
                category_rep.save(third_level);
            }
            product.setTitle(req.getTitle());
            product.setDescription(req.getDescription());
            product.setPrice(req.getPrice());
            product.setDiscountedPrice(req.getDiscount_price());
            product.setDiscountedPercent(req.getDiscount_percent());
            product.setQuantity(req.getQuantity());
            product.setBrand(req.getBrand());
            product.setColor(req.getColour());
            product.setSizes(req.getSize());
            product.setImage_URL(req.getImage_URL());
            product.setCreatedAt(LocalDateTime.now());
            Product saved = product_rep.save(product);

            return saved;
        }
        throw new ProductException("Product not found");

    }

    @Override
    public Product find_product_id(Long id) throws ProductException {
        Product product = product_rep.findProductById(id);
        if (product == null) {
            throw new ProductException("Product not found");
        }
        return product;
    }

    @Override
    public List<Product> find_product_category(String category) throws ProductException {
        List<Product> product = product_rep.findByCategoryName(category);
        if (product == null) {
            throw new ProductException("Product not found");
        }
        return product;
    }

    @Override
    public Page<Product> get_all_product(String category, List<String> colors, List<String> size, Integer min_price,
             String sort, String stock, Integer page_no, Integer paage_size) {

        Pageable pageable = PageRequest.of(page_no, paage_size);
        List<Product> products = product_rep.filterAndSort(category, min_price, sort);

        if (!colors.isEmpty()) {
            products = products.stream()
                    .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        /*
         * if (!size.isEmpty()) {
         * products = products.stream()
         * .filter(p -> p.getSizes().stream().anyMatch(s -> size.stream().anyMatch(ss ->
         * ss.equalsIgnoreCase(s.getName())))
         * .collect(Collectors.toList()));
         * }
         */

        if (stock != null) {
            if (stock.equals("in-stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else {
                products = products.stream().filter(p -> p.getQuantity() <= 0).collect(Collectors.toList());
            }
        }

        int startIdx = (int) pageable.getOffset();
        int endIdx = Math.min(startIdx + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIdx, endIdx);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProducts;
    }

    @Override
    public Page<Product> get_products(Integer page_no, Integer paage_size) {
        Pageable pageable = PageRequest.of(page_no, paage_size);
        List<Product> products = product_rep.findAll();

        int startIdx = (int) pageable.getOffset();
        int endIdx = Math.min(startIdx + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIdx, endIdx);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProducts;
    }

    @Override
    public List<Product> Products() {
        List<Product> products = product_rep.findAll();
        return products;
    }

}
