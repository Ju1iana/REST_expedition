package com.example.rest_expedition.service;

import com.example.rest_expedition.mail.IMail;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMail implements IMail {

  private final JavaMailSender mailSender;

  public SendMail(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendMessage(String to, String name, String msg) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom("${spring.mail.username}");

    simpleMailMessage.setTo("uluana.pankratova@gmail.com");
    simpleMailMessage.setTo("greysaboter@gmail.com");
    simpleMailMessage.setText("От: " + name + "\n" + "Почта: " + to + "\n"+ msg);

    try {
      mailSender.send(simpleMailMessage);
      System.out.println("The email has been sent!");
    } catch (MailException e) {
      System.out.println("Some errors....");
      e.printStackTrace();
    }
  }
}
