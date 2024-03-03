package com.example.rest_expedition.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Mail {

  @Email
  @Pattern(regexp = "^[\\w-\\.] +@[\\w-]+(\\. [\\w-]+)*\\. [a-z]{2,}$")
  private String to;

  private String recipientName;
  private String subject;
  private String text;
  private String senderName;
}
