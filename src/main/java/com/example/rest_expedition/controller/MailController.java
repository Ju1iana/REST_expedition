package com.example.rest_expedition.controller;

import com.example.rest_expedition.mail.IMail;
import com.example.rest_expedition.service.SendMail;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/contact") // ?
public class MailController {

  private final IMail iMail;
  private final SendMail sendMail;


  public MailController(IMail iMail, SendMail sendMail) {
    this.iMail = iMail;
    this.sendMail = sendMail;
  }

  @Operation(summary = "Send email")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST, value = "/sendEmail")
  public void addMessage(@RequestParam String name,
                         @RequestParam @Email String email,
                         @RequestParam String msg){
    iMail.sendMessage(email, name, msg);
   /* sendMail.sendMessage(email, name, msg);*/
  }


}
