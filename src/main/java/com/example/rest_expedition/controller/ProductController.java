package com.example.rest_expedition.controller;

import com.example.rest_expedition.dto.ProductDTO;
import com.example.rest_expedition.service.PersonCalculator;
import com.example.rest_expedition.model.Product;
import com.example.rest_expedition.service.ProductCalculator;
import com.example.rest_expedition.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "pg3.sweb.ru")
public class ProductController {
  private final PersonCalculator personCalculator;
  private final ProductCalculator productCalculator;
  private final ProductService productService;
  private final ModelMapper mapper;

  @Autowired
  public ProductController(PersonCalculator personCalculator, ProductCalculator calculator, ProductService foodItemService, ModelMapper mapper) {
    this.mapper = mapper;
    this.productCalculator = calculator;
    this.productService = foodItemService;
    this.personCalculator = personCalculator;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getAll")
  public List<ProductDTO> getAll(){
    return productService.getAllProducts().stream().map(this::convertToProductDTO).collect(Collectors.toList());
  }

  @Operation(summary = "Only lunch is given")
  @RequestMapping(method = RequestMethod.GET, value = "/getByType")
  public List<ProductDTO> getProductsByType(){
    return productService.getProductsByType().stream().map(this::convertToProductDTO).collect(Collectors.toList());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getCalories")
  public List<Integer> getCalories(){
    personCalculator.calcHikingCalories();
    return productCalculator.initCalories();
  }

  public ProductDTO convertToProductDTO(Product product){
    return mapper.map(product, ProductDTO.class);
  }
}
