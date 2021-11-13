package com.java.exercise.jpay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidationUtils {
  private static final String COUNTRY_CODE_EXTRACTION_REGEX = "\\((.*?)\\)";

  public static Integer findCountryCode(String phoneNumber) {
    Matcher m = Pattern.compile(COUNTRY_CODE_EXTRACTION_REGEX).matcher(phoneNumber);
    while (m.find()) {
      return Integer.parseInt(m.group(1));
    }
    return null;
  }
}
