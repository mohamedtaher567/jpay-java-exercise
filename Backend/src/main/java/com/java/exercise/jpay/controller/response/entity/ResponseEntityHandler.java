package com.java.exercise.jpay.controller.response.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityHandler {
  public static <T> ResponseEntity<T> handleResponseEntity(T body, HttpStatus status) {
    return new ResponseEntity<>(body, status);
  }

}
