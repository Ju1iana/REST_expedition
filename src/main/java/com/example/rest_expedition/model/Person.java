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
  private String FIO;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private PersonGender personGender;

  @Column(name = "age")
  private int age;

  @Column(name = "h")
  private int h;

  @Column(name = "weight")
  private int weight;

  @Column(name = "calories")
  private double calories;
}
