package com.java.exercise.jpay.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.exercise.jpay.dto.PhoneNumberState;
import com.java.exercise.jpay.exception.InvalidStateFilter;
import com.java.exercise.jpay.model.Country;
import com.java.exercise.jpay.model.PhoneNumber;
import com.java.exercise.jpay.service.PhoneNumbersService;

@Service("DummyPhoneNumberService")
public class DummyPhoneNumbersService implements PhoneNumbersService {
  private static List<PhoneNumber> dummyPhoneNumbers = new ArrayList<>();
  static {
    Country cameroon = new Country(1L, 237, "\\(237\\)\\ ?[2368]\\d{7,8}$");
    Country ethiopia = new Country(2L, 251, "\\(251\\)\\ ?[1-59]\\d{8}$");
    Country moroco = new Country(3L, 212, "\\(212\\)\\ ?[5-9]\\d{8}$");
    Country mozambique = new Country(4L, 258, "\\(258\\)\\ ?[28]\\d{7,8}$");
    Country uganda = new Country(5L, 256, "\\(256\\)\\ ?\\d{9}$");
    dummyPhoneNumbers.add(new PhoneNumber("1234", cameroon));
    dummyPhoneNumbers.add(new PhoneNumber("4321", cameroon));
    dummyPhoneNumbers.add(new PhoneNumber("1234", ethiopia));
    dummyPhoneNumbers.add(new PhoneNumber("4321", ethiopia));
    dummyPhoneNumbers.add(new PhoneNumber("(212) 698054317", moroco));
    dummyPhoneNumbers.add(new PhoneNumber("4321", moroco));
    dummyPhoneNumbers.add(new PhoneNumber("1234", mozambique));
    dummyPhoneNumbers.add(new PhoneNumber("4321", mozambique));
    dummyPhoneNumbers.add(new PhoneNumber("1234", uganda));
    dummyPhoneNumbers.add(new PhoneNumber("4321", uganda));
  };

  @Override
  public List<PhoneNumber> getPhoneNumbers(Long countryId, String state) {
    if (countryId == null && state == null) {
      return dummyPhoneNumbers;
    }
    return dummyPhoneNumbers.stream().filter(phoneNumber -> {
      try {
        return (countryId == null || phoneNumber.getCountry().getId().equals(countryId)) && (state == null || matchState(state, phoneNumber));
      } catch (InvalidStateFilter e) {
        // log error and consider phone number doesn't match
        Logger.getGlobal().log(new LogRecord(Level.WARNING, "Invalid state filter"));
        return false;
      }
    }).collect(Collectors.toList());
  }

  private boolean matchState(String state, PhoneNumber phoneNumber) throws InvalidStateFilter {
    boolean validPhoneNumber = phoneNumber.isValidPhoneNumber();
    try {
      PhoneNumberState stateEnum = PhoneNumberState.valueOf(state);
      switch (stateEnum) {
        case INVALID:
          return !validPhoneNumber;
        case VALID:
          return validPhoneNumber;
        default:
          throw new InvalidStateFilter();
      }
    } catch (Exception e) {
      throw new InvalidStateFilter();
    }
  }

}
