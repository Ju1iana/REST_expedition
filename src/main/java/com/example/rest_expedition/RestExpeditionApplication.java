package com.example.rest_expedition;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product API",version = "1.0",description = "Product web service"))
public class RestExpeditionApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestExpeditionApplication.class, args);
  }
}
