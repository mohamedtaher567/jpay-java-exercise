package com.java.exercise.jpay.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.exercise.jpay.constants.CountriesStore;
import com.java.exercise.jpay.controller.CountriesRestController;
import com.java.exercise.jpay.model.Country;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CountriesRestControllerTest extends RestControllerTest {
  private static final String COUNTRIES_URL = "/countries";
  @InjectMocks
  private CountriesRestController countriesController;

  private MockMvc mockMvc;

  @BeforeAll
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(countriesController).build();
  }

  @Test
  public void testValidRequest() throws Exception {
    List<Country> expectedResponse = new ArrayList<>(CountriesStore.COUNTRIES_STATIC_INFO.values());
    MvcResult mvcResult = this.mockMvc.perform(get(COUNTRIES_URL)).andExpect(status().isOk()).andReturn();
    MockHttpServletResponse response = mvcResult.getResponse();
    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(constructJson(expectedResponse), response.getContentAsString());
  }

}
