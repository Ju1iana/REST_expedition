package com.example.rest_expedition.controller;

import com.example.rest_expedition.dto.ProductDTO;
import com.example.rest_expedition.model.PersonCalculator;
import com.example.rest_expedition.model.Product;
import com.example.rest_expedition.model.RationCalculator;
import com.example.rest_expedition.service.ProductService;
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
@CrossOrigin(origins = "http://localhost:8080")
public class RationController {
/*  private final PersonCalculator personCalculator;*/
  private final RationCalculator rationCalculator;
  private final ProductService productService;
  private final ModelMapper mapper;

  @Autowired
  public RationController(PersonCalculator personCalculator, RationCalculator calculator, ProductService foodItemService, ModelMapper mapper) {
    /*this.personCalculator = personCalculator;*/
    this.mapper = mapper;
    this.rationCalculator = calculator;
    this.productService = foodItemService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getAll")
  public List<ProductDTO> getAll(){
    return productService.getAllProducts().stream().map(this::convertToProductDTO).collect(Collectors.toList());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getByType")
  public List<ProductDTO> getProductsByType(){
    return productService.getProductsByType().stream().map(this::convertToProductDTO).collect(Collectors.toList());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getCalories")
  public List<Integer> getCalories(){
    /*personCalculator.calcAll();*/
    return rationCalculator.initCalories();
  }

  public ProductDTO convertToProductDTO(Product product){
    return mapper.map(product, ProductDTO.class);
  }
}
