package com.example.rest_expedition.controller;

import com.example.rest_expedition.dto.MailDTO;
import com.example.rest_expedition.mail.IMail;
import com.example.rest_expedition.util.IncorrectEmailAddress;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController(value = "/contact") // ?
public class MailController {

  private final IMail iMail;

  public MailController(IMail iMail) {
    this.iMail = iMail;
  }

 /* @Operation(summary = "Send an email to the specified email address")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
  public void addMessage(@RequestParam String name,
                         @Valid @RequestParam @Email @NotNull @NotEmpty String email,
                         @RequestParam String msg) {
    iMail.sendMessage(email, name, msg);
  }*/

  @Operation(summary = "Send an email to the specified email address")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
  public void addMessage(@Valid @RequestBody MailDTO mailDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new IncorrectEmailAddress("Ошибка при вводе email");
    }

    iMail.sendMessage(mailDTO.getEmail(), mailDTO.getSenderName(), mailDTO.getText());
  }
}
