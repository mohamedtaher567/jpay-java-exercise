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

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

}
