package com.java.exercise.jpay.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.java.exercise.jpay.configuration.JpayApplicationTests;
import com.java.exercise.jpay.model.PhoneNumber;

public class PhoneNumberTest extends JpayApplicationTests {

  private static final String PHONE_NUMBER_2 = "4321";
  private static final int COUNTRY_CODE_1 = 212;
  private static final String PHONE_NUMBER_1 = "1234";

  @Test
  public void testEqualsSameObject() {
    PhoneNumber phoneNumber = createPhoneNumber(PHONE_NUMBER_1, COUNTRY_CODE_1);
    assertEquals(phoneNumber, phoneNumber);
  }

  @Test
  public void testEqualsTrue() {
    PhoneNumber phoneNumber1 = createPhoneNumber(PHONE_NUMBER_1, COUNTRY_CODE_1);
    PhoneNumber phoneNumber2 = createPhoneNumber(PHONE_NUMBER_1, COUNTRY_CODE_1);
    assertEquals(phoneNumber1, phoneNumber2);
  }

  @Test
  public void testEqualsWithDiffPhone() {
    PhoneNumber phoneNumber1 = createPhoneNumber(PHONE_NUMBER_1, COUNTRY_CODE_1);
    PhoneNumber phoneNumber2 = createPhoneNumber(PHONE_NUMBER_2, COUNTRY_CODE_1);
    assertNotEquals(phoneNumber1, phoneNumber2);
  }

  @Test
  public void testEqualsWithNull() {
    PhoneNumber phoneNumber1 = createPhoneNumber(PHONE_NUMBER_1, COUNTRY_CODE_1);
    assertNotEquals(phoneNumber1, null);
  }

}
