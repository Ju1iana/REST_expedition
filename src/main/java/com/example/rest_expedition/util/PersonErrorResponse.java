package com.example.rest_expedition.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonErrorResponse {

  private String message;
  private long timestamp;

}
