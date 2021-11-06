package com.java.exercise.jpay.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = {JpayApplicationTests.class})
@EntityScan("com.java.exercise.jpay.model")
@ComponentScan(basePackages = {"com.java.exercise.jpay.*"})
@EnableAutoConfiguration
public class JpayApplicationTests {

}
