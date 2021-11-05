package com.java.exercise.jpay.dto;

import java.util.Set;

public class PhoneNumbersFilterParams {
  private Set<String> states;
  private Set<Integer> countriesCodes;
  private Integer pageNumber;
  private Integer pageSize;

  public Set<String> getStates() {
    return states;
  }

  public void setStates(Set<String> states) {
    this.states = states;
  }

  public Set<Integer> getCountriesCodes() {
    return countriesCodes;
  }

  public void setCountriesCodes(Set<Integer> countriesCodes) {
    this.countriesCodes = countriesCodes;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

}
