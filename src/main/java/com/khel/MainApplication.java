package com.khel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class })
public class MainApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(MainApplication.class, args);
  }
}
