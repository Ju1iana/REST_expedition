package com.example.rest_expedition.service;

import com.example.rest_expedition.model.Product;
import com.example.rest_expedition.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts (){
    return productRepository.findAll();
  }

  // только на обед
  public List<Product> getProductsByType(){
    return productRepository.getProductByType();
  }

}
