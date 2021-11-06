package com.java.exercise.jpay.service;

import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.dto.PhoneNumbersResponse;

public interface PhoneNumbersService {

  PhoneNumbersResponse getPhoneNumbers(PhoneNumbersFilterParams filterParams);
}
