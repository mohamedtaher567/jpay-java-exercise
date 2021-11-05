package com.java.exercise.jpay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.java.exercise.jpay.utils.PhoneNumberValidationUtils;

@Entity
@Table(name = "customer")
public class Customer extends HasId {

  @Column
  private String name;
  @Column
  private String phone;
  @Transient
  @JsonProperty(access = Access.READ_ONLY)
  private Integer countryCode;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getCountryCode() {
    return countryCode;
  }

  @PostLoad
  private void setCountryCode() {
    this.countryCode = PhoneNumberValidationUtils.findCountryCode(phone);
  }
}
