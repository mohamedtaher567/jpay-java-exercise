package com.java.exercise.jpay.dto;

public class PhoneNumbersFilterParams {
  private PhoneNumberState state;
  private Long countryId;
  private int pageNumber;
  private int pageSize;

  public PhoneNumberState getState() {
    return state;
  }

  public void setState(PhoneNumberState state) {
    this.state = state;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

}
