package com.java.exercise.jpay.service;

import java.util.List;

import com.java.exercise.jpay.dto.PhoneNumber;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;

public interface PhoneNumbersService {

  List<PhoneNumber> getPhoneNumbers(PhoneNumbersFilterParams filterParams);
}
