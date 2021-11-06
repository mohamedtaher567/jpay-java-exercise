package com.java.exercise.jpay.dto;

public enum PhoneNumberState {
  VALID("Valid"), INVALID("Invalid");

  private String displaName;

  private PhoneNumberState(String displayName) {
    this.displaName = displayName;
  }

  public String getDisplaName() {
    return displaName;
  }

}
