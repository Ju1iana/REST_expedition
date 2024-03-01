package com.example.rest_expedition.repository;

import com.example.rest_expedition.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  // вывести продукты только на завтрак

     @Query(value = "SELECT p.product_id, product_name, product_calories_per_100g, product_daily_norm_per_person," +
    "product_proteins, product_fats, product_carbohydrates, product_digestibility_percentage\n" +
    "FROM mix m\n" +
    "         JOIN Products p ON m.product_id = p.product_id\n" +
    "         JOIN Product_types mt ON m.type_id = mt.type_id\n" +
    "WHERE m.type_id = 2",nativeQuery = true)
  List<Product> getProductByType();
}
