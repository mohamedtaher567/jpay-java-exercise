package com.java.exercise.jpay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.java.exercise.jpay.configuration.JpayApplicationTests;
import com.java.exercise.jpay.dto.PhoneNumber;
import com.java.exercise.jpay.dto.PhoneNumberState;
import com.java.exercise.jpay.dto.PhoneNumbersResponse;
import com.java.exercise.jpay.model.Customer;
import com.java.exercise.jpay.repository.CustomerRepository;
import com.java.exercise.jpay.service.impl.CustomerPhoneNumbersServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class CustomerPhoneNumbersServiceTest extends JpayApplicationTests {

  private static final Set<Integer> INVALID_INDICIES = new HashSet<>();
  private static final String MOROCO = "Moroco";
  private static final String ETHIOPIA = "Ethiopia";
  private static final String CAMEROON = "Cameroon";
  private static final String MOZAMBIQUE = "Mozambique";

  @InjectMocks
  private CustomerPhoneNumbersServiceImpl phoneNumersService;

  @Mock
  private CustomerRepository customerRepository;

  private static List<PhoneNumber> TEST_PHONE_NUMBERS = new ArrayList<>();

  private static List<Customer> customers = new ArrayList<>();

  @BeforeAll
  public void setup() {
    // Invalid
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(212) 6007989253", MOROCO));
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(212) 698054317", MOROCO));
    // Invalid
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(212) 6546545369", MOROCO));
    // Invalid
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(251) 9773199405", ETHIOPIA));
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(251) 966002259", ETHIOPIA));
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(251) 924418461", ETHIOPIA));
    // Invalid
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(237) 6A0311634", CAMEROON));
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(237) 673122155", CAMEROON));
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(258) 847651504", MOZAMBIQUE));
    TEST_PHONE_NUMBERS.add(createPhoneNumber("(258) 847651504", MOZAMBIQUE));
    TEST_PHONE_NUMBERS.forEach(phoneNumber -> {
      customers.add(createCustomer(phoneNumber.getNumber()));
    });
    INVALID_INDICIES.add(0);
    INVALID_INDICIES.add(2);
    INVALID_INDICIES.add(3);
    INVALID_INDICIES.add(6);

  }

  @BeforeEach
  public void mockCustomerRepositoryResponse() {
    when(customerRepository.findAll()).thenReturn(customers);
  }

  @Test
  public void testFindAllPhoneNumbers() {
    PhoneNumbersResponse phoneNumbers = phoneNumersService.getPhoneNumbers(createPhoneNumbersFilterParams(0, 10));
    assertEquals(TEST_PHONE_NUMBERS, phoneNumbers.getPhoneNumbers());
  }

  @Test
  public void testFilterCountryCode() {
    Set<Integer> countryCodes = new HashSet<>();
    countryCodes.add(212);
    countryCodes.add(258);
    PhoneNumbersResponse phoneNumbers = phoneNumersService.getPhoneNumbers(createPhoneNumbersFilterParams(0, 10, null, countryCodes));
    assertEquals(TEST_PHONE_NUMBERS.stream()
        .filter(phoneNumber -> phoneNumber.getCountryName().equals(MOROCO) || phoneNumber.getCountryName().equals(MOZAMBIQUE))
        .collect(Collectors.toList()), phoneNumbers.getPhoneNumbers());
  }

  @Test
  public void testFilterStates1() {
    Set<String> states = new HashSet<>();
    states.add(PhoneNumberState.INVALID.toString());
    PhoneNumbersResponse phoneNumbers = phoneNumersService.getPhoneNumbers(createPhoneNumbersFilterParams(0, 10, states, null));
    assertEquals(filterPhoneNumbers(TEST_PHONE_NUMBERS, INVALID_INDICIES, true), phoneNumbers.getPhoneNumbers());
  }

  @Test
  public void testFilterStates2() {
    Set<String> states = new HashSet<>();
    states.add(PhoneNumberState.VALID.toString());
    PhoneNumbersResponse phoneNumbers = phoneNumersService.getPhoneNumbers(createPhoneNumbersFilterParams(0, 10, states, null));
    assertEquals(filterPhoneNumbers(TEST_PHONE_NUMBERS, INVALID_INDICIES, false), phoneNumbers.getPhoneNumbers());
  }

  @Test
  public void testFilterStates3() {
    Set<String> states = new HashSet<>();
    states.add(PhoneNumberState.VALID.toString());
    states.add(PhoneNumberState.INVALID.toString());
    PhoneNumbersResponse phoneNumbers = phoneNumersService.getPhoneNumbers(createPhoneNumbersFilterParams(0, 10, states, null));
    assertEquals(TEST_PHONE_NUMBERS, phoneNumbers.getPhoneNumbers());
  }

  @Test
  public void testFilterCountryCodesAndStates() {
    Set<Integer> countryCodes = new HashSet<>();
    countryCodes.add(237);
    countryCodes.add(251);
    Set<String> states = new HashSet<>();
    states.add(PhoneNumberState.INVALID.toString());
    PhoneNumbersResponse phoneNumbers = phoneNumersService.getPhoneNumbers(createPhoneNumbersFilterParams(0, 10, states, countryCodes));
    assertEquals(filterPhoneNumbers(TEST_PHONE_NUMBERS, Sets.newLinkedHashSet(3, 6), true), phoneNumbers.getPhoneNumbers());
  }

  private static Customer createCustomer(String phoneNumber) {
    Customer customer = new Customer();
    customer.setPhone(phoneNumber);
    return customer;
  }

  private static List<PhoneNumber> filterPhoneNumbers(List<PhoneNumber> phoneNumbers, Set<Integer> indicies, boolean include) {
    List<PhoneNumber> newPhoneNumbers = new ArrayList<>();
    for (int i = 0; i < phoneNumbers.size(); i++) {
      if ((include && indicies.contains(i)) || (!include && !indicies.contains(i))) {
        newPhoneNumbers.add(phoneNumbers.get(i));
      }
    }
    return newPhoneNumbers;
  }
}
