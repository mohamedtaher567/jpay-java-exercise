package com.java.exercise.jpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.exercise.jpay.controller.response.entity.ResponseEntityHandler;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.dto.PhoneNumbersResponse;
import com.java.exercise.jpay.exceptions.InvalidParametersException;
import com.java.exercise.jpay.service.PhoneNumbersService;
import com.java.exercise.jpay.utils.FilterParametersValidationUtils;

@RequestMapping("phoneNumbers")
@RestController
@CrossOrigin
public class PhoneNumbersRestController {
  @Autowired
  @Qualifier("CustomerPhoneNumersService")
  private PhoneNumbersService phoneNumbersService;

  @PostMapping
  public ResponseEntity<PhoneNumbersResponse> getPhoneNumbers(@RequestBody PhoneNumbersFilterParams filterParams) {
    PhoneNumbersResponse response = new PhoneNumbersResponse();
    HttpStatus status = HttpStatus.OK;
    try {
      FilterParametersValidationUtils.validateFilterParameters(filterParams);
      response = phoneNumbersService.getPhoneNumbers(filterParams);
      if (CollectionUtils.isEmpty(response.getPhoneNumbers())) {
        status = HttpStatus.NO_CONTENT;
      }
    } catch (InvalidParametersException e) {
      response.setErrorMessage(e.getMessage());
      status = HttpStatus.BAD_REQUEST;
    }
    return ResponseEntityHandler.handleResponseEntity(response, status);
  }

}
