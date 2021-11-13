package com.java.exercise.jpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Country {
  @JsonIgnore
  private String validationRegex;
  private String name;
  private Integer countryCode;

  public Country(Integer countryCode, String name, String validationRegex) {
    this.countryCode = countryCode;
    this.name = name;
    this.validationRegex = validationRegex;
  }

  public Integer getCountryCode() {
    return countryCode;
  }

  public String getName() {
    return name;
  }

  public String getValidationRegex() {
    return validationRegex;
  }

}
