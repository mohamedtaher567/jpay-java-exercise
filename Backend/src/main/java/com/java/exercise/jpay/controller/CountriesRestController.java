package com.java.exercise.jpay.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.exercise.jpay.constants.CountriesStore;
import com.java.exercise.jpay.controller.response.entity.ResponseEntityHandler;
import com.java.exercise.jpay.model.Country;

@RequestMapping("countries")
@RestController
@CrossOrigin
public class CountriesRestController {
  @GetMapping
  public ResponseEntity<Collection<Country>> getCountries() {
    return ResponseEntityHandler.handleResponseEntity(CountriesStore.COUNTRIES_STATIC_INFO.values(), HttpStatus.OK);
  }
}
