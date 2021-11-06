package com.java.exercise.jpay.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories("com.java.exercise.jpay.repository")
@EntityScan("com.java.exercise.jpay.model")
@ComponentScan(basePackages = {"com.java.exercise.jpay.*"})
@EnableAutoConfiguration
public class JpayApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpayApplication.class, args);
  }

}
