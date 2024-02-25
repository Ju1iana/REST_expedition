package com.example.rest_expedition.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "fio")
  @NotEmpty(message = "Name should not be empty")
  @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
  private String FIO;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private PersonGender personGender;

  @Column(name = "age")
  @Min(value = 0, message = "Age should be greater then 0")
  @Max(value = 122, message = "Age should be less than 122")
  private int age;

  @Min(value = 0, message = "Height should be greater then 0")
  @Max(value = 250, message = "Height should be less than 272")
  @Column(name = "h")
  private int h;

  @Min(value = 0, message = "Weight should be greater then 0")
  @Max(value = 200, message = "Weight should be less than 272")
  @Column(name = "weight")
  private int weight;

  @Column(name = "calories")
  private double calories;
}
