package com.java.exercise.jpay.controller.response.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityHandler {
  public static ResponseEntity<?> validRequestResponse(Object body) {
    return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
  }

  public static ResponseEntity<String> invalidRequestResponse(String message) {
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }
}
