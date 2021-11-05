package com.java.exercise.jpay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.exercise.jpay.constants.CountriesStore;
import com.java.exercise.jpay.dto.PhoneNumberState;

public class PhoneNumberValidationUtils {
  private static final String COUNTRY_CODE_EXTRACTION_REGEX = "\\((.*?)\\)";

  public static PhoneNumberState getPhoneNumberState(String phoneNumber, Integer countryCode) {
    boolean validPhoneNumber = Pattern.matches(CountriesStore.COUNTRIES_VALIDATION_REGEX.get(countryCode).getValidationRegex(), phoneNumber);
    PhoneNumberState stateEnum = null;
    if (validPhoneNumber) {
      stateEnum = PhoneNumberState.VALID;
    } else {
      stateEnum = PhoneNumberState.INVALID;
    }
    return stateEnum;
  }

  public static Integer findCountryCode(String phoneNumber) {
    Matcher m = Pattern.compile(COUNTRY_CODE_EXTRACTION_REGEX).matcher(phoneNumber);
    while (m.find()) {
      return Integer.parseInt(m.group(1));
    }
    return null;
  }
}
