package com.java.exercise.jpay.constants;

import java.util.HashMap;
import java.util.Map;

import com.java.exercise.jpay.model.Country;

public class CountriesStore {
  public static final Map<Integer, Country> COUNTRIES_STATIC_INFO = new HashMap<>();
  static {
    COUNTRIES_STATIC_INFO.put(237, new Country(237, "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
    COUNTRIES_STATIC_INFO.put(251, new Country(251, "Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$"));
    COUNTRIES_STATIC_INFO.put(212, new Country(212, "Moroco", "\\(212\\)\\ ?[5-9]\\d{8}$"));
    COUNTRIES_STATIC_INFO.put(258, new Country(258, "Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$"));
    COUNTRIES_STATIC_INFO.put(256, new Country(256, "Uganda", "\\(256\\)\\ ?\\d{9}$"));
  }
}
