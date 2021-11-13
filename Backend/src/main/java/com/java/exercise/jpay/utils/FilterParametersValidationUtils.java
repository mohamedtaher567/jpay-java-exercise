package com.java.exercise.jpay.utils;

import com.java.exercise.jpay.controller.ResponseMessages;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.exceptions.InvalidParametersException;

public class FilterParametersValidationUtils {
  public static void validateFilterParameters(PhoneNumbersFilterParams filterParams) throws InvalidParametersException {
    String errorMessage = null;
    if (filterParams.getPageNumber() == null) {
      errorMessage = ResponseMessages.MISSING_PAGE_NUMBER;
    } else if (filterParams.getPageSize() == null) {
      errorMessage = ResponseMessages.MISSING_PAGE_SIZE;
    } else if (filterParams.getPageSize() <= 0) {
      errorMessage = ResponseMessages.INVALID_PAGE_SIZE;
    } else if (filterParams.getPageNumber() <= 0) {
      errorMessage = ResponseMessages.INVALID_PAGE_NUMBER;
    }
    if (errorMessage != null) {
      throw new InvalidParametersException(errorMessage);
    }
  }
}
