package com.example.rest_expedition.util;

public class IncorrectEmailAddress extends RuntimeException{
  public IncorrectEmailAddress(String msg){
    super(msg);
  }
}
