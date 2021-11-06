package com.java.exercise.jpay.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.java.exercise.jpay.constants.CountriesStore;
import com.java.exercise.jpay.dto.PhoneNumber;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.dto.PhoneNumbersResponse;
import com.java.exercise.jpay.repository.CustomerRepository;
import com.java.exercise.jpay.service.PhoneNumbersService;
import com.java.exercise.jpay.utils.PhoneNumberValidationUtils;

@Service("CustomerPhoneNumersService")
public class CustomerPhoneNumbersServiceImpl implements PhoneNumbersService {

  @Autowired
  private CustomerRepository customerRepo;

  @Override
  public PhoneNumbersResponse getPhoneNumbers(PhoneNumbersFilterParams filterParams) {
    PhoneNumbersResponse phoneNumbers = new PhoneNumbersResponse();
    phoneNumbers.setPhoneNumbers(customerRepo.findAll().stream().filter(customer -> {
      Set<Integer> coutnryCodes = filterParams.getCountriesCodes();
      Set<String> states = filterParams.getStates();
      String phoneNumber = customer.getPhone();
      Integer countryCode = PhoneNumberValidationUtils.findCountryCode(phoneNumber);
      return (CollectionUtils.isEmpty(coutnryCodes) || coutnryCodes.contains(countryCode)) && (CollectionUtils.isEmpty(states)
          || states.contains(PhoneNumberValidationUtils.getPhoneNumberState(phoneNumber, countryCode).toString()));
    }).skip(filterParams.getPageNumber() * filterParams.getPageSize()).limit(filterParams.getPageSize()).map(customer -> {
      String phoneNumber = customer.getPhone();
      return new PhoneNumber(phoneNumber,
          CountriesStore.COUNTRIES_VALIDATION_REGEX.get(PhoneNumberValidationUtils.findCountryCode(phoneNumber)).getName());
    }).collect(Collectors.toList()));
    return phoneNumbers;
  }

}
