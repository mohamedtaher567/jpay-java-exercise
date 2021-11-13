package com.java.exercise.jpay.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.exercise.jpay.configuration.JpayApplicationTests;

public abstract class RestControllerTest extends JpayApplicationTests {
  protected static String constructJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }
}
