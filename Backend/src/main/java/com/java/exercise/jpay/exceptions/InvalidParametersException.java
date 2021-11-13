package com.java.exercise.jpay.exceptions;

public class InvalidParametersException extends Exception {
  String errorMessage;

  public InvalidParametersException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String getMessage() {
    return errorMessage;
  }

}
