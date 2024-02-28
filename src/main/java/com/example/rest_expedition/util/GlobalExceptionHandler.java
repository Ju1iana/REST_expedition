package com.example.rest_expedition.util;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e) {
    PersonErrorResponse response = new PersonErrorResponse(
      "Person with this ID wasn't found!!",
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404
  }

  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handlerException(PersonNotCreatedException e) {
    PersonErrorResponse response = new PersonErrorResponse(
      e.getMessage(),
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400
  }
}
