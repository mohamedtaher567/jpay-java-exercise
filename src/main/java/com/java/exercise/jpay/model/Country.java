package com.java.exercise.jpay.model;

public class Country extends HasId {
  private Integer countryCode;
  private String validationRegex;

  public Country(Long id, Integer countryCode, String validationRegex) {
    setId(id);
    this.countryCode = countryCode;
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

}
