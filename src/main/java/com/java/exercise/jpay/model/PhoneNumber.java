package com.java.exercise.jpay.model;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PhoneNumber extends HasId {
  private String number;
  private Country country;

  public PhoneNumber(String number, Country country) {
    this.number = number;
    this.country = country;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @JsonIgnore
  public boolean isValidPhoneNumber() {
    return Pattern.matches(country.getValidationRegex(), number);
  }
}
