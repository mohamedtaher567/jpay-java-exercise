package com.java.exercise.jpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.exercise.jpay.controller.response.entity.ResponseEntityHandler;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.service.PhoneNumbersService;

@RequestMapping("phoneNumbers")
@RestController
public class PhoneNumbersRestController {
  @Autowired
  @Qualifier("CustomerPhoneNumersService")
  private PhoneNumbersService phoneNumbersService;

  @PostMapping
  public ResponseEntity<?> getPhoneNumbers(@RequestBody PhoneNumbersFilterParams filterParams) {
    if (filterParams.getPageNumber() == null) {
      return ResponseEntityHandler.invalidRequestResponse(ResponseMessages.MISSING_PAGE_NUMBER);
    } else if (filterParams.getPageSize() == null) {
      return ResponseEntityHandler.invalidRequestResponse(ResponseMessages.MISSING_PAGE_SIZE);
    } else if (filterParams.getPageSize() <= 0) {
      return ResponseEntityHandler.invalidRequestResponse(ResponseMessages.INVALID_PAGE_SIZE);
    } else if (filterParams.getPageNumber() < 0) {
      return ResponseEntityHandler.invalidRequestResponse(ResponseMessages.INVALID_PAGE_NUMBER);
    }
    return ResponseEntityHandler.validRequestResponse(phoneNumbersService.getPhoneNumbers(filterParams));
  }
}
