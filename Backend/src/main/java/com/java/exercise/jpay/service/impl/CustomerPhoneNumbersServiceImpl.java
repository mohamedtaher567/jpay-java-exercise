package com.java.exercise.jpay.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.java.exercise.jpay.dto.PhoneNumberState;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.dto.PhoneNumbersResponse;
import com.java.exercise.jpay.model.PhoneNumber;
import com.java.exercise.jpay.repository.PhoneNumberRepository;
import com.java.exercise.jpay.service.PhoneNumbersService;

@Service("CustomerPhoneNumersService")
public class CustomerPhoneNumbersServiceImpl implements PhoneNumbersService {

  @Autowired
  private PhoneNumberRepository phoneNumberRepo;

  @Override
  public PhoneNumbersResponse getPhoneNumbers(PhoneNumbersFilterParams filterParams) {
    PhoneNumbersResponse phoneNumbers = new PhoneNumbersResponse();
    List<PhoneNumber> filteredPhoneNumbers = null;
    Set<Boolean> validSet = null;
    Set<String> states = filterParams.getStates();
    if (!CollectionUtils.isEmpty(states)) {
      validSet = states.stream().map(state -> PhoneNumberState.VALID.toString().equals(state)).collect(Collectors.toSet());
    }
    PageRequest pageable = PageRequest.of(filterParams.getPageNumber() - 1, filterParams.getPageSize());
    Set<Integer> countriesCodes = filterParams.getCountriesCodes();
    Long totalCount = 0L;
    if (CollectionUtils.isEmpty(validSet) && CollectionUtils.isEmpty(countriesCodes)) {
      filteredPhoneNumbers = phoneNumberRepo.findAll(pageable).getContent();
      totalCount = phoneNumberRepo.count();
    } else if (CollectionUtils.isEmpty(countriesCodes)) {
      filteredPhoneNumbers = phoneNumberRepo.findByValidIn(validSet, pageable);
      totalCount = phoneNumberRepo.countByValidIn(validSet);
    } else if (CollectionUtils.isEmpty(validSet)) {
      filteredPhoneNumbers = phoneNumberRepo.findByCountryCodeIn(countriesCodes, pageable);
      totalCount = phoneNumberRepo.countByCountryCodeIn(countriesCodes);
    } else {
      filteredPhoneNumbers = phoneNumberRepo.findByValidInAndCountryCodeIn(validSet, countriesCodes, pageable);
      totalCount = phoneNumberRepo.countByValidInAndCountryCodeIn(validSet, countriesCodes);
    }
    phoneNumbers.setTotalCount(totalCount);
    phoneNumbers.setPhoneNumbers(filteredPhoneNumbers);
    return phoneNumbers;
  }

}
