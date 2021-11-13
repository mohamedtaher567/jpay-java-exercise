package com.java.exercise.jpay.service.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.exercise.jpay.configuration.JpayApplicationTests;
import com.java.exercise.jpay.utils.PhoneNumberValidationUtils;

@ExtendWith(MockitoExtension.class)
public class PhoneNumberValidationUtilsTest extends JpayApplicationTests {
  private final static String PHONE_NUMBER_WITH_COUNTRY_CODE = "(212) 6007989253";
  private final static String PHONE_NUMBER_WITHOUT_COUNTRY_CODE = "6007989253";
  private final static Integer COUNTRY_CODE = 212;

  @Test
  public void testCountryCodeExtraction() throws Exception {
    assertEquals(COUNTRY_CODE, PhoneNumberValidationUtils.findCountryCode(PHONE_NUMBER_WITH_COUNTRY_CODE));
  }

  @Test
  public void testMissingCountryCode() throws Exception {
    assertNull(PhoneNumberValidationUtils.findCountryCode(PHONE_NUMBER_WITHOUT_COUNTRY_CODE));
  }
}
