package com.java.exercise.jpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.exercise.jpay.model.PhoneNumber;
import com.java.exercise.jpay.service.PhoneNumbersService;

@RequestMapping("api/v1/phoneNumbers")
@RestController
public class PhoneNumbersRestController {
  @Autowired
  private PhoneNumbersService phoneNumbersService;

  @GetMapping
  public List<PhoneNumber> getPhoneNumbers(@RequestParam(required = false) Long countryId, @RequestParam(required = false) String state) {
    return phoneNumbersService.getPhoneNumbers(countryId, state);
  }
}
