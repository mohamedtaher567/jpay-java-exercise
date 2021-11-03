package com.java.exercise.jpay.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.java.exercise.jpay.*")
public class JpayApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpayApplication.class, args);
  }

}
