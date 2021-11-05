package com.java.exercise.jpay.model;

public class Country {
  private Integer countryCode;
  private String validationRegex;
  private String name;

  public Country(Integer countryCode, String name, String validationRegex) {
    this.countryCode = countryCode;
    this.name = name;
    this.validationRegex = validationRegex;
  }

  public Integer getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(Integer countryCode) {
    this.countryCode = countryCode;
  }

  public String getValidationRegex() {
    return validationRegex;
  }

  public void setValidationRegex(String validationRegex) {
    this.validationRegex = validationRegex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
