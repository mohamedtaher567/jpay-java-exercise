package com.java.exercise.jpay.configuration;

import java.util.Set;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.model.PhoneNumber;

@SpringBootTest(classes = {JpayApplicationTests.class})
@EntityScan("com.java.exercise.jpay.model")
@ComponentScan(basePackages = {"com.java.exercise.jpay.*"})
@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class JpayApplicationTests {

  protected static PhoneNumbersFilterParams createPhoneNumbersFilterParams(Integer pageNumber, Integer pageSize) {
    return createPhoneNumbersFilterParams(pageNumber, pageSize, null, null);
  }

  protected static PhoneNumbersFilterParams createPhoneNumbersFilterParams(Integer pageNumber, Integer pageSize, Set<String> states,
      Set<Integer> countryCodes) {
    PhoneNumbersFilterParams phoneNumbersFilterParams = new PhoneNumbersFilterParams();
    phoneNumbersFilterParams.setPageNumber(pageNumber);
    phoneNumbersFilterParams.setPageSize(pageSize);
    phoneNumbersFilterParams.setStates(states);
    phoneNumbersFilterParams.setCountriesCodes(countryCodes);
    return phoneNumbersFilterParams;
  }

  protected PhoneNumber createPhoneNumber(String phoneNumber, Integer countryCode) {
    return new PhoneNumber(phoneNumber, countryCode);
  }

}
