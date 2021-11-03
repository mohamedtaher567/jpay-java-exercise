package com.java.exercise.jpay.service;

import java.util.List;

import com.java.exercise.jpay.model.PhoneNumber;

public interface PhoneNumbersService {

  List<PhoneNumber> getPhoneNumbers(Long countryId, String state);
}
