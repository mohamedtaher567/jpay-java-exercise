package com.java.exercise.jpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Country {
  @JsonIgnore
  private String validationRegex;
  private String name;

  public Country(String name, String validationRegex) {
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
