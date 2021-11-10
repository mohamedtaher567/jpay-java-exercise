package com.java.exercise.jpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Country {
  @JsonIgnore
  private String validationRegex;
  private String name;
  private Integer countryCode;

  public Country(Integer countryCode, String name, String validationRegex) {
    this.name = name;
    this.countryCode = countryCode;
    this.validationRegex = validationRegex;
  }

  public String getValidationRegex() {
    return validationRegex;
  }

  public String getName() {
    return name;
  }

  public Integer getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(Integer countryCode) {
    this.countryCode = countryCode;
  }
}
