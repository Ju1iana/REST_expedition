package com.example.rest_expedition.controller;

import com.example.rest_expedition.mail.IMail;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/contact") // ?
public class MailController {

  private final IMail iMail;

  public MailController(IMail iMail) {
    this.iMail = iMail;
  }

  @Operation(summary = "Send email")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
  public void addMessage(@RequestParam String name,
                         @Valid @RequestParam @Email @NotNull @NotEmpty String email,
                         @RequestParam String msg) {
    iMail.sendMessage(email, name, msg);
  }
}
