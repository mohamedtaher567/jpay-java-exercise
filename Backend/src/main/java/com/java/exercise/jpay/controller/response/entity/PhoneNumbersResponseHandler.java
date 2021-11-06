package com.java.exercise.jpay.controller.response.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.java.exercise.jpay.dto.PhoneNumbersResponse;

public class PhoneNumbersResponseHandler {
  public static ResponseEntity<PhoneNumbersResponse> handleResponseEntity(PhoneNumbersResponse body, HttpStatus status) {
    return new ResponseEntity<>(body, status);
  }

}
