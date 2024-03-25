package com.example.rest_expedition.model;

import com.example.rest_expedition.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Ration {

  public int day;

  public String nameOfMeal;

  public List<ProductDTO> products;
}
