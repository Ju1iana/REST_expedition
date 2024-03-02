package com.example.rest_expedition.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Mail {
  @Email
  private String to;
  private String recipientName;
  private String subject;
  private String text;
  private String senderName;
}
