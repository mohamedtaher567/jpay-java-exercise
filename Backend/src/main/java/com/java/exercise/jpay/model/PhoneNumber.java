package com.java.exercise.jpay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.java.exercise.jpay.constants.CountriesStore;

@Entity
@Table(name = "phone_number")
public class PhoneNumber extends HasId {

  @Column
  private String number;
  @Transient
  private String countryName;
  @Column(name = "country_code")
  private Integer countryCode;
  @Column
  private Boolean valid;

  public PhoneNumber() {}

  public PhoneNumber(String number, Integer countryCode) {
    this.number = number;
    this.countryCode = countryCode;
  }

  public String getNumber() {
    return number;
  }

  public String getCountryName() {
    return CountriesStore.COUNTRIES_STATIC_INFO.get(countryCode).getName();
  }

  public Integer getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(Integer countryCode) {
    this.countryCode = countryCode;
  }

  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
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
    return number.equals(phoneNumber.getNumber());
  }
}
