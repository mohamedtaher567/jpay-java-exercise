package com.java.exercise.jpay.dto;

import java.util.List;

public class PhoneNumbersResponse {
  private List<PhoneNumber> phoneNumbers;
  private String errorMessage;

  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
