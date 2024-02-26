package com.example.rest_expedition;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestExpeditionApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestExpeditionApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }
}
