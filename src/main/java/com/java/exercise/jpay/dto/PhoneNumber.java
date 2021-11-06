package com.java.exercise.jpay.dto;

public class PhoneNumber {

  private String number;
  private String countryName;

  public PhoneNumber(String number, String countryName) {
    this.number = number;
    this.setCountryName(countryName);
  }

  public String getNumber() {
    return number;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null) {
      return false;
    }
    PhoneNumber phoneNumber = (PhoneNumber) other;
    return countryName.equals(phoneNumber.getCountryName()) && number.equals(phoneNumber.getNumber());
  }
}
