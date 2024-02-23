package com.example.rest_expedition.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
