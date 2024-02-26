package com.example.rest_expedition.dto;

import com.example.rest_expedition.model.PersonGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDTO {
  @NotEmpty(message = "Name should not be empty")
  @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
  private String fio;

  @Enumerated(EnumType.STRING)
  private PersonGender personGender;

  @Min(value = 0, message = "Age should be greater then 0")
  @Max(value = 122, message = "Age should be less than 122")
  private int age;

  @Min(value = 0, message = "Height should be greater then 0")
  @Max(value = 250, message = "Height should be less than 272")
  private int h;

  @Min(value = 0, message = "Weight should be greater then 0")
  @Max(value = 200, message = "Weight should be less than 272")
  private int weight;
}
