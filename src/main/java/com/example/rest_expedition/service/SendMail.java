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
    /*simpleMailMessage.setTo("greysaboter@gmail.com");*/

    simpleMailMessage.setText(
      "Имя отправителя: " + name + "\n" +
        "Почта отправителя: " + to + "\n" +
        "Текст сообщения: " + msg);

    try {
      mailSender.send(simpleMailMessage);
      System.out.println("\nThe message has been sent!");
      sendResponseMessage(to, name);
    } catch (MailException e) {
      System.out.println("Some errors....");
      e.printStackTrace();
    }
  }

  public void sendResponseMessage(String to, String name) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom("${spring.mail.username}");
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject("GoTravel");
    simpleMailMessage.setText("Здравствуйте, " + name + "! Ваше обращение было успешно отправлено. В ближайшее время мы обязательно свяжемся с вами! \n" +
      "Всего наилучшего от команды GoTravel :)");

    try {
      mailSender.send(simpleMailMessage);
      System.out.println("\nThe response message has been sent!");
    } catch (MailException e) {
      System.out.println("Some errors with answer....");
      e.printStackTrace();
    }
  }
}
