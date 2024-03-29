package com.example.rest_expedition.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MailDTO {
  private String senderName;

  @Email
  @Pattern(regexp = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}",
    message = "Ваш формат email не соответствует формату электронной почты")
  private String email;
  private String text;
}
