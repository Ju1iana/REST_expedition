package com.example.rest_expedition.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private int id;

  @Column(name = "product_name")
  private String name;

  @Column(name = "product_calories_per_100g")
  private int calories_per_100g;

  @Column(name = "product_daily_norm_per_person")
  private int daily_norm_per_person;

  @Column(name = "product_proteins")
  private int proteins;

  @Column(name = "product_fats")
  private int fats;

  @Column(name = "product_carbohydrates")
  private int carbohydrates;

  @Column(name = "product_digestibility_percentage")
  private int digestibility_percentage;

  // TODO: реализовать equals и hashcode
}
