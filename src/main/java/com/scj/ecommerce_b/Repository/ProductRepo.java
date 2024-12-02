package com.scj.ecommerce_b.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scj.ecommerce_b.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

        @Query("SELECT p FROM Product p WHERE p.id=:id")
        public Product findProductById(@Param("id") Long id);

        public List<Product> findByCategoryName(String category);

        @Query("SELECT p FROM Product p " +
                        "WHERE (p.category.name = :Category OR :Category = '') " +
                        "AND ((:MinDiscount IS NULL) OR (p.discountedPercent >= :MinDiscount))" + "ORDER BY " +
                        "CASE WHEN :sort = 'Price: Low to High' THEN p.discountedPrice END ASC, " +
                        "CASE WHEN :sort = 'Price: High to Low' THEN p.discountedPrice END DESC")

        public List<Product> filterAndSort(@Param("Category") String category,
                         @Param("MinDiscount") Integer min_discount,
                        @Param("sort") String Sort);

}
