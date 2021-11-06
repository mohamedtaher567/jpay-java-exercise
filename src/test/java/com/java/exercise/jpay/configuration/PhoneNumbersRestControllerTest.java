package com.java.exercise.jpay.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.exercise.jpay.controller.PhoneNumbersRestController;
import com.java.exercise.jpay.controller.ResponseMessages;
import com.java.exercise.jpay.dto.PhoneNumber;
import com.java.exercise.jpay.dto.PhoneNumbersFilterParams;
import com.java.exercise.jpay.dto.PhoneNumbersResponse;
import com.java.exercise.jpay.service.PhoneNumbersService;

@TestInstance(Lifecycle.PER_CLASS)
public class PhoneNumbersRestControllerTest extends JpayApplicationTests {
  private static final String PHONE_NUMBERS_URL = "/phoneNumbers";

  @InjectMocks
  private PhoneNumbersRestController phoneNumbersController;

  @Mock
  private PhoneNumbersService phoneNumbersService;

  @Captor
  private ArgumentCaptor<PhoneNumbersFilterParams> phoneNumbersFilterParamsCaptor;

  private MockMvc mockMvc;

  @BeforeAll
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(phoneNumbersController).build();
  }

  @Test
  public void testValidRequest() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(0, 10));
    PhoneNumbersResponse expectedResponseObject = constructResponse(Lists.list(new PhoneNumber("123", "000")), null);
    String expectedResponse = constructJson(expectedResponseObject);
    when(phoneNumbersService.getPhoneNumbers(phoneNumbersFilterParamsCaptor.capture())).thenReturn(expectedResponseObject);
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isOk()), HttpStatus.OK.value());
    verifyServiceHits(1);
  }

  @Test
  public void testMissingPageNumber() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(null, 10));
    String expectedResponse = constructJson(constructResponse(null, ResponseMessages.MISSING_PAGE_NUMBER));
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isBadRequest()), HttpStatus.BAD_REQUEST.value());
    verifyServiceHits(0);
  }

  @Test
  public void testMissingPageSize() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(0, null));
    String expectedResponse = constructJson(constructResponse(null, ResponseMessages.MISSING_PAGE_SIZE));
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isBadRequest()), HttpStatus.BAD_REQUEST.value());
    verifyServiceHits(0);
  }

  @Test
  public void testInvalidPageSize1() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(0, 0));
    String expectedResponse = constructJson(constructResponse(null, ResponseMessages.INVALID_PAGE_SIZE));
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isBadRequest()), HttpStatus.BAD_REQUEST.value());
    verifyServiceHits(0);
  }

  @Test
  public void testInvalidPageSize2() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(0, -1));
    String expectedResponse = constructJson(constructResponse(null, ResponseMessages.INVALID_PAGE_SIZE));
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isBadRequest()), HttpStatus.BAD_REQUEST.value());
    verifyServiceHits(0);
  }

  @Test
  public void testInvalidPageNumber() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(-1, 10));
    String expectedResponse = constructJson(constructResponse(null, ResponseMessages.INVALID_PAGE_NUMBER));
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isBadRequest()), HttpStatus.BAD_REQUEST.value());
    verifyServiceHits(0);
  }

  @Test
  public void testEmptyContent() throws Exception {
    String payload = constructJson(createPhoneNumbersFilterParams(0, 10));
    PhoneNumbersResponse expectedResponseObject = constructResponse(Lists.newArrayList(), null);
    String expectedResponse = constructJson(expectedResponseObject);
    when(phoneNumbersService.getPhoneNumbers(phoneNumbersFilterParamsCaptor.capture())).thenReturn(expectedResponseObject);
    verifyResponse(expectedResponse, performAndMatchStatus(payload, status().isNoContent()), HttpStatus.NO_CONTENT.value());
    verifyServiceHits(1);
  }

  private static void verifyResponse(String expectedResponse, MvcResult mvcResult, Integer statusCode) throws UnsupportedEncodingException {
    MockHttpServletResponse response = mvcResult.getResponse();
    assertEquals(statusCode, response.getStatus());
    assertEquals(expectedResponse, response.getContentAsString());
  }

  private MvcResult performAndMatchStatus(String body, ResultMatcher expectedStatus) throws Exception {
    MvcResult mvcResult =
        this.mockMvc.perform(post(PHONE_NUMBERS_URL).content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(expectedStatus).andReturn();
    return mvcResult;
  }

  private void verifyServiceHits(int n) {
    verify(phoneNumbersService, times(n)).getPhoneNumbers(any());
  }

  private static PhoneNumbersFilterParams createPhoneNumbersFilterParams(Integer pageNumber, Integer pageSize) {
    PhoneNumbersFilterParams phoneNumbersFilterParams = new PhoneNumbersFilterParams();
    phoneNumbersFilterParams.setPageNumber(pageNumber);
    phoneNumbersFilterParams.setPageSize(pageSize);
    return phoneNumbersFilterParams;
  }

  private static String constructJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }

  private static PhoneNumbersResponse constructResponse(List<PhoneNumber> phoneNumbers, String errorMessage) {
    PhoneNumbersResponse response = new PhoneNumbersResponse();
    response.setPhoneNumbers(phoneNumbers);
    response.setErrorMessage(errorMessage);
    return response;
  }

}
