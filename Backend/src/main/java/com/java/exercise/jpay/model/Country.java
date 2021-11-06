package com.java.exercise.jpay.model;

public class Country {
  private String validationRegex;
  private String name;

  public Country(Integer countryCode, String name, String validationRegex) {
    this.name = name;
    this.validationRegex = validationRegex;
  }

  public String getValidationRegex() {
    return validationRegex;
  }

  public String getName() {
    return name;
  }

}
