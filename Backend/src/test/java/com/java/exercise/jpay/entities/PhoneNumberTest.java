package com.java.exercise.jpay.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.java.exercise.jpay.configuration.JpayApplicationTests;
import com.java.exercise.jpay.dto.PhoneNumber;

public class PhoneNumberTest extends JpayApplicationTests {

  @Test
  public void testEqualsSameObject() {
    PhoneNumber phoneNumber = createPhoneNumber("1234", "country1");
    assertEquals(phoneNumber, phoneNumber);
  }

  @Test
  public void testEqualsTrue() {
    PhoneNumber phoneNumber1 = createPhoneNumber("1234", "country1");
    PhoneNumber phoneNumber2 = createPhoneNumber("1234", "country1");
    assertEquals(phoneNumber1, phoneNumber2);
  }

  @Test
  public void testEqualsWithDiffCountry() {
    PhoneNumber phoneNumber1 = createPhoneNumber("1234", "country1");
    PhoneNumber phoneNumber2 = createPhoneNumber("1234", "country2");
    assertNotEquals(phoneNumber1, phoneNumber2);
  }

  @Test
  public void testEqualsWithDiffPhone() {
    PhoneNumber phoneNumber1 = createPhoneNumber("1234", "country1");
    PhoneNumber phoneNumber2 = createPhoneNumber("4321", "country1");
    assertNotEquals(phoneNumber1, phoneNumber2);
  }

  @Test
  public void testEqualsWithNull() {
    PhoneNumber phoneNumber1 = createPhoneNumber("1234", "country1");
    assertNotEquals(phoneNumber1, null);
  }

}