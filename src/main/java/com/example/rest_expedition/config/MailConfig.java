package com.example.rest_expedition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static org.springframework.mail.javamail.JavaMailSenderImpl.DEFAULT_PROTOCOL;

@PropertySource(value = {"classpath:gmail.com.properties"})
@Configuration
public class MailConfig {

  @Value("${spring.mail.host}")
  private String mailHost;

  @Value("${spring.mail.port}")
  private Integer mailPort;

  @Value("${spring.mail.username}")
  private String mailUsername;

  @Value("${spring.mail.password}")
  private String mailPassword;

  @Bean
  public JavaMailSender getJavaMailSender(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(mailHost);
    mailSender.setPort(mailPort);
    mailSender.setUsername(mailUsername);
    mailSender.setPassword(mailPassword);
    mailSender.setProtocol(DEFAULT_PROTOCOL);

    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.starttls.enable", true);
    properties.put("mail.debug", "true");

    return mailSender;
  }
}
