package com.example.rest_expedition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/index")
  public String indexPage() {
    return "index";
  }

  @GetMapping("/contact")
  public String contactPage() {
    return "contact";
  }

}
